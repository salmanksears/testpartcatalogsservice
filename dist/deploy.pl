###################################HARD CODE HERE#######################################

#hostname is target staging server

#$hostname = "trypdejweb1.intra.searshc.com";
#$scp_target_directory = '/appl/dej/deployables';

@deploy[0] = 'prod1';
#@deploy[1] = 'prod2';

my %scpdata = ('prod1'  =>     ["trprhspsolrm1.vm.itg.corp.us.shldcorp.com", "", "",'/appl/hspartscatalog/deployables','prod'],
	           'prod2'   =>    ["trypdejweb2.intra.searshc.com", "", "",'/appl/elasticsearch/deployables','prod']
                );


##########################################################################################


#Call looks like this: $cmd = "deploy.pl $scp_userid $scp_password $prod_tag $tag_url $pacman_num";

use Sys::Hostname;
use Net::FTP;
use File::Basename;
use Term::ReadKey;

our $ccyymmdd;
our $scp_username;
our $scp_password;
our $prod_tag;
our $svn_url;
our $pacman_num;
our $export_dir;
our @scp_list;
our $date;



sub send_mail {

	 
	my $logfile = $_[0];

	my $Mailserver = "ustrysvpldsmtp.searshc.com";
	my $Mailfrom = "scmadm";
 
	$email = "Config_mgmt_pager";
	#$email = "scmadm";
	 
	$emailsubject = "SVN deployment issue: $pacman_num";
 	
	my $cmd = "D:\\blat32\\blat $logfile -t $email -f \"$Mailfrom\" -s \"$emailsubject\" -server $Mailserver";
	print "$cmd\n";
	`$cmd`;
}

sub CheckArguments {

	$scp_username =$ARGV[0];
 	$scp_password =$ARGV[1];
 
 	$prod_tag = $ARGV[2];
 	$tag_url = $ARGV[3];
	$pacman_num = $ARGV[4]; 
	
}

sub rename_files {

	$tag = $_[0];
	($label, $date, $time) = split(/_/,$tag);
	$date =~ s/-//g; 

	#$export_dir = "D\:\\temp\\mydir";

	$ch_dir = chdir($export_dir);
	if (!$ch_dir) {
		print LOG "Problem changing directory to where files have been exported:\n Check for directory: $export_dir on server\n";
			       send_mail($local_debug_script_log);
			       die "Problem changing directory to export directory $export_dir .... \n";
	}
	else
	{
	
		opendir DH, $export_dir;
		print "\n\n\n";
		foreach $f (readdir DH) {
			next if $f =~ /^\./;
			next if $f eq 'deploy.pl';
			
			my ( $t_file, $t_path, $t_suffix ) = fileparse($f, qr{\..*});
			$renamed_f = $t_file . '_' . $date . '_01-prod' . $t_suffix;
			$renamed_f = 'AUTO_' . $renamed_f;
			 
			rename $f, $renamed_f;
			push (@scp_list, $renamed_f);
			
		}
	}

}


sub export_files 
{

	$new_dir = $_[0];
	$export_url = $_[1];
	$svn_exports = "D\:\\svn_exports\\";
	
	$export_dir = $svn_exports . $new_dir;
	$ch_dir = chdir($export_dir); # cd D:\svn_exports\prod_tag

            	if (!$ch_dir) 
		{
			$ch_dir = chdir($svn_exports);
			if (!$ch_dir)
			{
				print "export folder $svn_exports does not exist, please create it\n";		 
				return 1; 
			}
			else
			{
				mkdir $export_dir, 0755 or warn "Cannot make directory!\n";
				$ch_dir = chdir($export_dir);
			}
			
		}
		 

		$export_url = $export_url . '/dist/';
		$cmd = "svn export $export_url";
		$result = system $cmd;
		if ($result) { 

			       print LOG "Problem with url:\n $export_url\nCheck whether folder exists for the repo number in PACMAN: $pacman_num";
			       send_mail($local_debug_script_log);
			       die "Problem exporting url $export_url .... \n";
			     }
		else {
		     $export_dir = $export_dir . '\dist';
		     print "export directory is\n$export_dir";
		     }
		
}

sub scp_files {
	#.\pscp.exe ima1day2wcs.war confgmgt@Ustrysvpjbosa1.searshc.com:/appl/ima1day2wcs/deployables
  	
	$hostname = $scpdata{@_[0]}[0];
	$scp_target_directory = $scpdata{$_[0]}[3];	
	foreach (@scp_list)
	{
		$cmd = "D\:\\svn\\pscp.exe -pw $scp_password $_ $scp_username\@$hostname\:$scp_target_directory";
		#print "$_ \n";
		$fcmd = "D\:\\svn\\pscp.exe -pw - $_ $scp_username\@$hostname\:$scp_target_directory";
		$result = system $cmd;
		if ($result) { 
				print LOG "scp failed to send file $_ to $scp_target_directory on $hostname\n Check directory permissions and space availability."; 
				send_mail($local_debug_script_log);
			        die "Problem with scp to $hostname:$scp_target_directory .... \n";
			      }
		else {

			print LOG "\n$fcmd\n";  
	 		#($auto, $f, $p) = split(/_/);
			#print "tfile name is... $f\n";
			
    			#my $flag_file = "complete_" . $f; 
			my $flag_file = "complete_" . $date; 
			my $job = "D\:\\svn_exports\\$flag_file" . ".$$";
			open SCP, "> $job"; 
			print SCP "$date";
			close SCP;
			my $srcfile = $job;
			$keep = $scp_target_directory;
			$scp_target_directory = $scp_target_directory . '/' . $flag_file . '.txt';
			my $cmd = "D:\\svn\\pscp.exe -pw $scp_password $srcfile $scp_username\@$hostname\:$scp_target_directory";
			$result = system $cmd;
			print "$cmd\n";
			$scp_target_directory = $keep;
		}
	}

} 


sub main {
	#print "$scp_username $scp_password $prod_tag \n";
	#print "$svn_url\n";
	#print "$pacman_num\n";




$prod_tag = substr($prod_tag,1);
$prod_tag =~ s/:/-/g;
print "$prod_tag\n";

export_files ($prod_tag, $tag_url);
rename_files ($prod_tag);

foreach (@deploy){
	scp_files( $_);
}


}



CheckArguments();

$local_debug_script_log = "D\:\\svn_exports\\$pacman_num.log";
open LOG, ">$local_debug_script_log"; 

main();


