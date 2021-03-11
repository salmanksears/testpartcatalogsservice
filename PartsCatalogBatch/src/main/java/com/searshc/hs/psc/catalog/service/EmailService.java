package com.searshc.hs.psc.catalog.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.searshc.hs.psc.catalog.util.Constants;
import com.searshc.hs.psc.catalog.vo.Audit;

@Component
public class EmailService {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	private Properties props;

	@Value("${email.toAddress}")
	private String toAddress;
	@Value("${email.host}")
	private String host;

	@PostConstruct
	private void init() {
		props = new Properties();
		props.put("mail.smtp.host", host);
	}

	public void send(Audit audit) throws Exception {

		try {
			SimpleDateFormat osdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			Date date = osdf.parse(audit.getFileTs());
			SimpleDateFormat esdf = new SimpleDateFormat("yyyy-M-d h:m:s");
			String ets = esdf.format(date);

			Session session = Session.getDefaultInstance(props, null);

			Multipart multipart = new MimeMultipart();

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("no-reply@sears.com"));
			List<String> toAddList = new ArrayList<>(Arrays.asList(toAddress.split(",")));
			String updEmail = audit.getFileUploadedBy().trim() + Constants.EMAIL_DOMAIN;
			if(!toAddList.contains(updEmail)){
				toAddList.add(updEmail);
			}
			InternetAddress[] toAddresses = InternetAddress.parse(StringUtils.join(toAddList, ", "));
			message.setRecipients(Message.RecipientType.TO, toAddresses);
			
			message.setSubject(audit.getName() + ":" + audit.getFileOperation() + " BULK UPLOAD - " + ets);

			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(getBodyText(audit, ets));
			multipart.addBodyPart(bodyPart);

			// error file...
			if (audit.getErrorCnt() > 0 || audit.isFileUnprocessed()) {
				multipart.addBodyPart(getAttachment(audit));
				message.setContent(multipart);
			}

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			LOGGER.debug("Email SUCCESSFULLY sent to {}....", toAddList);

		} catch (Exception e) {
			LOGGER.error("Failed to send e-mail message:{}", e.getMessage(), e);
			throw e;
		}
	}

	private String getBodyText(Audit audit, String ts) {

		StringBuilder sb = new StringBuilder();

		String NEWLINE = System.getProperty("line.separator");

		sb.append("BULK UPDATE: ").append(audit.getName()).append(NEWLINE);
		sb.append("TIME STAMP: ").append(ts).append(NEWLINE);
		sb.append("RECORDS READ: ").append(audit.getRecordCnt()).append(NEWLINE);
		sb.append("INSERT CNT: ").append(audit.getInsertCnt()).append(NEWLINE);
		sb.append("UPDATE CNT: ").append(audit.getUpdateCnt()).append(NEWLINE);
		sb.append("DELETE CNT: ").append(audit.getDeleteCnt()).append(NEWLINE);
		sb.append("ERROR CNT: ").append(audit.getErrorCnt()).append(NEWLINE);
		sb.append("SUCCESS CNT: ").append(audit.getSuccessCnt()).append(NEWLINE);
		sb.append("ORIGINAL FILE NAME: ").append(audit.getOrginalFileName()).append(NEWLINE);
		sb.append("FILE UPLOADED BY: ").append(audit.getFileUploadedBy()).append(NEWLINE);
		sb.append("FILE UPLOADED TIME: ").append(audit.getFileUploadTime()).append(NEWLINE);
		sb.append("MESSAGE: ").append(audit.getMessage()).append(NEWLINE);
		return sb.toString();
	}

	private BodyPart getAttachment(Audit audit) throws Exception {

		DataSource dataSource = new ByteArrayDataSource(Files.readAllBytes(Paths.get(audit.getErrFile().getPath())),
				"text/plain");
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setDataHandler(new DataHandler(dataSource));
		bodyPart.setFileName(audit.getErrFile().getName());

		return bodyPart;
	}
}
