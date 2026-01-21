package com.inqwise.opinion.account;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.inqwise.opinion.common.Uid;
import com.inqwise.opinion.common.UidPrefixGenerator;

import io.vertx.sqlclient.templates.RowMapper;
import io.vertx.sqlclient.templates.TupleMapper;

class DaoMappers {
	public static final TupleMapper<AccountIdentity> IDENTITY_PARAMS = TupleMapper.mapper(identity -> {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_account_id", identity.getId());
		return parameters;
	});
			
	public static final RowMapper<Account> ACCOUNT_ROW = row -> {
		var builder = Account.builder();
		Long id = row.getLong("account_id");
		builder.withId(id)
		.withUidToken(Uid.builder().withId(id).withPrefix(row.getString("uid_prefix")).build().toUidToken())
		.withProductId(row.getInteger("product_id"))
		.withOwnerId(row.getLong("owner_id"))
		//owner_name
		//sp_name
		.withName(row.getString("account_name"))
		.withServicePackageId(row.getInteger("service_package_id"))
		.withServicePackageExpiredOn(row.getLocalDate("service_package_expiry_date"))
		.withActive(row.getBoolean("is_active"))
		//status_id
		.withCreatedAt(row.getLocalDateTime("insert_date"))
		.withTimezoneOffset(row.getInteger("time_offset"));
		
		if(row.getColumnIndex("max_users") > -1) {
			builder.withMaxUsers(row.get(Integer.class, "max_users"));
		}

		if(row.getColumnIndex("min_deposit_amount") > -1) {
			builder.withMinDepositAmount(row.getInteger("min_deposit_amount"));
		}
		
		if(row.getColumnIndex("max_deposit_amount") > -1) {
			builder.withMaxDepositAmount(row.getInteger("max_deposit_amount"));
		}
		
		return builder.build();
	};

	public static final TupleMapper<CreateRequest> getCreateParams(UidPrefixGenerator generator){
		return TupleMapper.mapper(request -> { 
			Map<String, Object> parameters = new HashMap<>();
			String uidPrefix = generator.generate();
			parameters.put("p_client_ip", request.getClientIp());
			parameters.put("p_country_id", request.getCountryId());
			parameters.put("p_account_name", request.getName());
			parameters.put("p_uid_prefix", uidPrefix);
			parameters.put("p_source_id", request.getSourceId());
			parameters.put("p_product_id", request.getProductId());
			parameters.put("p_service_package_id", request.getServicePackageId());
			parameters.put("p_user_id", request.getUserId());
			parameters.put("p_backoffice_user_id", request.getBackofficeUserId());
			return parameters;
		});
	}

	public static final RowMapper<CreateResult> CREATE_RESULT_ROW = row -> {
		var builder = CreateResult.builder();
		Long id = row.getLong("account_id");
		builder
		.withId(id)
		.withUidToken(Uid.builder()
				.withId(id)
				.withPrefix(row.getString("uid_prefix"))
				.build().toUidToken());
		return builder.build();
	};

	public static final TupleMapper<SearchRequest> SEARCH_REQUEST = TupleMapper.mapper(request -> { 
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_user_id", request.getUserId());
		parameters.put("p_product_id", request.getProductId());
		parameters.put("p_include_non_active", request.getIncludeNonActive());
		parameters.put("p_row_count", request.getSize());
		parameters.put("p_offset", null);
		parameters.put("p_from_insert_date", request.getFrom());
		parameters.put("p_to_insert_date", request.getTo());
		String collect = null;
		if(null != request.getIds()) {
			collect = request.getIds().stream().map(String::valueOf).collect(Collectors.joining(","));
		}
		parameters.put("p_account_id_list", collect);
		return parameters;
	});

	public static final String SEARCH_TEMPLATE =  "CALL getAccounts("
			+ "#{p_user_id},#{p_product_id},"
			+ "#{p_include_non_active},#{p_offset},#{p_row_cout},#{p_from_insert_date},"
			+ "#{p_to_insert_date},#{p_account_id_list}"
			+ ")";

	public static final String GET_TEMPLATE = "CALL getAccount("
			+ "#{p_account_id})";

	public static final String CREATE_TEMPLATE = "CALL createUserAccount("
			+ "#{p_client_ip},#{p_country_id},"
			+ "#{p_account_name},#{p_uid_prefix},#{p_source_id},#{p_product_id},#{p_service_package_id},"
			+ "#{p_user_id},#{p_backoffice_user_id}"
			+ ")";
	
	public static final String DELETE_TEMPLATE = "CALL deleteAccount("
			+ "#{p_account_id})";
	
	
	public static final TupleMapper<ModifyRequest> ATTACH_USER_PARAMS = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_source_id", request.getSourceId());
		parameters.put("p_user_id", request.getUserId());
		parameters.put("p_account_id", request.getId());
		parameters.put("p_backoffice_user_id", request.getBackofficeUserId());
		return parameters;
	});
	
	public static final String ATTACH_USER_TEMPLATE = "CALL attachUserAccount("
			+ "#{p_source_id},"
			+ "#{p_user_id},#{p_account_id},#{p_backoffice_user_id}"
			+ ")";
	
	public static final String DETACH_USER_TEMPLATE = "CALL detachUserAccount("
			+ "#{p_source_id},"
			+ "#{p_user_id},#{p_account_id},#{p_backoffice_user_id}"
			+ ")";
	
	public static final TupleMapper<ModifyRequest> DETACH_USER_REQUEST = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_source_id", request.getSourceId());
		parameters.put("p_user_id", request.getUserId());
		parameters.put("p_account_id", request.getId());
		parameters.put("p_backoffice_user_id", request.getBackofficeUserId());
		return parameters;
	});
	
	public static final String CHANGE_OWNER_TEMPLATE = "CALL changeAccountOwner("
			+ "#{p_source_id},"
			+ "#{p_owner_id},"
			+ "#{p_account_id},"
			+ "#{p_backoffice_user_id}"
			+ ")";
	
	public static final TupleMapper<ModifyRequest> CHANGE_OWNER_REQUEST = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_source_id", request.getSourceId());
		parameters.put("p_owner_id", request.getOwnerId());
		parameters.put("p_account_id", request.getAccountId());
		parameters.put("p_backoffice_user_id", request.getBackofficeUserId());
		return parameters;
	});
	
	public static final String CHANGE_BALANCE_TEMPLATE = "CALL changeBalance("
			+ "#{p_account_id},"
			+ "#{p_backoffice_user_id},#{p_comments},#{p_amount},#{p_$accop_type_id},"
			+ "#{p_source_guid},#{p_session_id},#{p_geo_country_code},#{p_client_ip}"
			+ ")";
	
	public static final TupleMapper<ModifyRequest> CHANGE_BALANCE_PARAMS = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_account_id", request.getId());
		parameters.put("backoffice_user_id", request.getBackofficeUserId());
		parameters.put("comments", request.getComments());
		parameters.put("amount", request.getAmount());
		parameters.put("$accop_type_id", request.getAccopTypeId());
		parameters.put("$source_guid", request.getSourceGuid());
		parameters.put("$session_id", request.getSessionId());
		parameters.put("geo_country_code", request.getGeoCountryCode());
		parameters.put("client_ip", request.getClientIp());
		return parameters;
	});
	
	public static final String GET_BALANCE_TEMPLATE = "CALL getFreeBalance("
			+ "#{p_account_id},"
			+ "#{p_user_id}"
			+ ")";
	
	public static final TupleMapper<AccountIdentity> GET_BALANCE_PARAMS = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_account_id", request.getId());
		parameters.put("p_user_id", request.getUserId());
		return parameters;
	});
	
	public static final String UPDATE_TEMPLATE = "CALL setAccountDetails("
			+ "#{p_account_id},#{p_comments},"
			+ "#{p_timezone_id},#{p_account_name},#{p_is_active},"
			+ "#{p_owner_id},#{p_include_deposit_bounds},"
			+ "#{p_min_deposit_amount},#{p_max_deposit_amount}"
			+ ")";
	
	public static final TupleMapper<ModifyRequest> UPDATE_PARAMS = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_account_id", request.getId());
		parameters.put("p_comments", request.getComments());
		parameters.put("p_timezone_id", request.getTimezoneId());
		parameters.put("p_account_name", request.getAccountName());
		parameters.put("p_is_active", request.getIsActive());
		parameters.put("p_owner_id", request.getOwnerId());
		parameters.put("p_include_deposit_bounds", request.getIncludeDepositBounds());
		parameters.put("p_min_deposit_amount", request.getMinDepositAmount());
		parameters.put("p_max_deposit_amount", request.getMaxDepositAmount());
		return parameters;
	});
	
	public static final String UPDATE_BUSINESS_DETAILS_TEMPLATE = "CALL setAccountBusinessDetails("
			+ "#{p_account_id},#{p_business_company_name},#{p_business_first_name},"
			+ "#{p_business_last_name},#{p_business_address1},#{p_business_address2},"
			+ "#{p_business_city},#{p_business_country_id},#{p_business_state_id},"
			+ "#{p_business_postal_code},#{p_business_phone1}"
			+ ");";
	
	public static final TupleMapper<ModifyRequest> UPDATE_BUSINESS_DETAILS_PARAMS = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_account_id", request.getAccountId());
		parameters.put("p_business_company_name", request.getBusinessCompanyName());
		parameters.put("p_business_first_name", request.getBusinessFirstName());
		parameters.put("p_business_last_name", request.getBusinessLastName());
		parameters.put("p_business_address1", request.getBusinessAddress1());
		parameters.put("p_business_address2", request.getBusinessAddress2());
		parameters.put("p_business_city", request.getBusinessCity());
		parameters.put("p_business_country_id", request.getBusinessCountryId());
		parameters.put("p_business_state_id", request.getBusinessStateId());
		parameters.put("p_business_postal_code", request.getBusinessPostalCode());
		parameters.put("p_business_phone1", request.getBusinessPhone1());
		return parameters;
	});
	
	public static final String GET_BUSINESS_DETAILS_TEMPLATE = "CALL getAccountBusinessDetails("
			+ "#{p_account_id}"
			+ ");";
	
	public static final TupleMapper<AccountIdentity> GET_BUSINESS_DETAILS_PARAMS = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_account_id", request.getId());
		return parameters;
	});
	
	public static final String GET_BY_USER_AND_PRODUCT_TEMPLATE = "CALL getAccountsByUserIdAndProduct("
			+ "#{p_user_id},"
			+ "#{p_product_id}"
			+ ");";
	
	public static final TupleMapper<SearchRequest> GET_BY_USER_AND_PRODUCT_PARAMS = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_user_id", request.getUserId());
		parameters.put("p_product_id", request.getProductId());
		return parameters;
	});
	
	public static final String LIST_EXPIRED_SERVICE_PACKAGE_TEMPLATE = "CALL getAccountsWithExpiredServicePackages("
			+ "#{p_expiry_date}"
			+ ");";
	
	public static final TupleMapper<SearchRequest> EXPIRED_SERVICE_PACKAGE_PARAMS = TupleMapper.mapper(request ->{
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_expiry_date", request.getExpiryAt());
		return parameters;
	});
}
