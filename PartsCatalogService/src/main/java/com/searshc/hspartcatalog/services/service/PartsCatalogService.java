package com.searshc.hspartcatalog.services.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.searshc.hspartcatalog.services.vo.base.BaseResponse;
import com.searshc.hspartcatalog.services.vo.request.GetCoreAndEnvChargeAmountRequest;
import com.searshc.hspartcatalog.services.vo.response.GetCoreAndEnvChargeAmountResponse;

@Path("/")
public interface PartsCatalogService {

	@GET
	@Path("/ping")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response ping();
	
	@GET
	@Path("/itemSearch")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse itemSearch(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("itemId") String itemId,
			@QueryParam("partNo") String partNo,
			@QueryParam("productGroupId") String productGroupId,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("formatted") String formatted,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@Context UriInfo uriInfo);
	
	@GET
	@Path("/itemDescriptionSearch")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse itemDescriptionSearch(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("brand") String brand,
			@QueryParam("productType") String productType,
			@QueryParam("modelNo")String modelNo,
			@QueryParam("description")String description,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@Context UriInfo uriInfo);
	
			
	@GET
	@Path("/modelSearch")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse modelSearch(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("modelNo")String modelNo,
			@QueryParam("modelId")String modelId,
			@QueryParam("brand")String brand,
			@QueryParam("parentProductTypeId")String parentProductTypeId,
			@QueryParam("productTypeId")String productTypeId,
			@QueryParam("subProductTypeId")String subProductTypeId,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("formatted") String formatted,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@Context UriInfo uriInfo);
	
	@GET
	@Path("v1_0/modelSearch")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse modelSearchEnhanced(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("modelNo")String modelNo,
			@QueryParam("modelId")String modelId,
			@QueryParam("brand")String brand,
			@QueryParam("parentProductTypeId")String parentProductTypeId,
			@QueryParam("productTypeId")String productTypeId,
			@QueryParam("subProductTypeId")String subProductTypeId,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("facetBy") String facetBy,
			@QueryParam("enableSearsIdSearch") String enableSearsIdSearch,
			@QueryParam("enableFuzzySearch") String enableFuzzySearch,
			@Context UriInfo uriInfo);
	
	@GET
	@Path("v1_1/modelSearch")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse modelSearchWild(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("modelNo")String modelNo,
			@QueryParam("brand")String brand,
			@QueryParam("parentProductTypeId")String parentProductTypeId,
			@QueryParam("productTypeId")String productTypeId,
			@QueryParam("subProductTypeId")String subProductTypeId,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("facetBy") String facetBy,
			@QueryParam("enableFuzzySearch") String enableFuzzySearch,
			@Context UriInfo uriInfo);
	
	@GET
	@Path("v1_2/modelSearch")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse modelNumSearch(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("modelNo")String modelNo,
			@QueryParam("brand")String brand,
			@QueryParam("parentProductTypeId")String parentProductTypeId,
			@QueryParam("productTypeId")String productTypeId,
			@QueryParam("subProductTypeId")String subProductTypeId,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("facetBy") String facetBy,
			@QueryParam("enableFuzzySearch") String enableFuzzySearch,
			@Context UriInfo uriInfo);
	
	@GET
	@Path("v1_1/modelSearchBySearsId")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse modelSearchBySearsId(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("modelNo")String modelNo,
			@QueryParam("brand")String brand,
			@QueryParam("parentProductTypeId")String parentProductTypeId,
			@QueryParam("productTypeId")String productTypeId,
			@QueryParam("subProductTypeId")String subProductTypeId,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("facetBy") String facetBy,
			@Context UriInfo uriInfo);
	

	@GET
	@Path("v1_1/modelSearchFuzzy")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse modelSearchFuzzy(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("modelNo")String modelNo,
			@QueryParam("modelId")String modelId,
			@QueryParam("brand")String brand,
			@QueryParam("parentProductTypeId")String parentProductTypeId,
			@QueryParam("productTypeId")String productTypeId,
			@QueryParam("subProductTypeId")String subProductTypeId,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@QueryParam("facetBy") String facetBy,
			@Context UriInfo uriInfo);
	
	
	@GET
	@Path("/fullSearch")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse fullSearch(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("key")String key,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("formatted") String formatted,
			@QueryParam("startingRow") String startingRow,
			@QueryParam("maxRows") String maxRows,
			@QueryParam("sortBy") String sortBy,
			@Context UriInfo uriInfo);
	
	@GET
	@Path("/getBrands")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getBrands(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("brand")String brand,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("sortBy") String sortBy,
			@Context UriInfo uriInfo);
	

	@GET
	@Path("/getSchematics")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getSchematics(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("modelNo")String modelNo,
			@QueryParam("modelId")String modelId,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("sortBy") String sortBy,
			@Context UriInfo uriInfo);
	

	@GET
	@Path("/getModelDetails")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getModelDetails(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("modelId") String modelId,
			@HeaderParam("Accept") String responseFormat,
			@Context UriInfo uriInfo);

	@GET
	@Path("/getModelsForItem")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getModelsForItem(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("itemId") String itemId,
			@HeaderParam("Accept") String responseFormat, 
			@Context UriInfo uriInfo);
	
	@GET
	@Path("/getItemDetails")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getItemDetails(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("itemId") String itemId,
			@HeaderParam("Accept") String responseFormat, 
			@Context UriInfo uriInfo);

	@GET
	@Path("/getItemList")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getItemList(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("productGroupId") String productGroupId, 
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow, 
			@QueryParam("maxRows") String maxRows, 
			@Context UriInfo uriInfo);

	@GET
	@Path("/getModelList")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getModelList(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("parentProductTypeId") String parentProductTypeId, 
			@QueryParam("brandId") String brandId, 
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow, 
			@QueryParam("maxRows") String maxRows, 
			@Context UriInfo uriInfo);
 

	
	@GET
	@Path("/getBrandList")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getBrandList(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("parentProductTypeId") String parentProductTypeId, 
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow, 
			@QueryParam("maxRows") String maxRows, 
			@Context UriInfo uriInfo);

	
	@GET
	@Path("/getParentProductTypeList")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getParentProductTypeList(
			@HeaderParam("API-Key") String clientKey,
			@QueryParam("brandId") String brandId, 
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow, 
			@QueryParam("maxRows") String maxRows, 
			@Context UriInfo uriInfo);

	
	@GET
	@Path("/getProductGroupList")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getProductGroupList(
			@HeaderParam("API-Key") String clientKey,
			@HeaderParam("Accept") String responseFormat,
			@QueryParam("startingRow") String startingRow, 
			@QueryParam("maxRows") String maxRows, 
			@Context UriInfo uriInfo);

	
	@GET
	@Path("/accessories/{productTypeId}/{brandId}/{modelNo}")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse getAccessories(
			@HeaderParam("API-Key") String clientKey,
			@HeaderParam("Accept") String responseFormat,
			@PathParam("productTypeId")String productTypeId,
			@PathParam("brandId")String brandId,
			@PathParam("modelNo")String modelNo,
			@QueryParam("maxRows") String maxRows,
			@Context UriInfo uriInfo,
			@Context HttpHeaders headers);
	
	@POST
	@Path("/getCoreAndEnvChargeAmount")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public GetCoreAndEnvChargeAmountResponse getCoreAndEnvChargeAmount(
			@HeaderParam("API-Key") String clientKey, @Context UriInfo uriInfo,GetCoreAndEnvChargeAmountRequest coreAndEnvChargeAmountRequest);

	@GET
	@Path("/search")
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public BaseResponse search(
		@HeaderParam("API-Key") String clientKey,
		@HeaderParam("Accept") String responseFormat,
		@QueryParam("value")String value,
			@QueryParam("startingRow") String startingRow,
		@QueryParam("maxRows") String maxRows,
		@Context UriInfo uriInfo);
}	
