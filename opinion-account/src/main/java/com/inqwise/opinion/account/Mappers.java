package com.inqwise.opinion.account;

import java.util.HashMap;
import java.util.Map;

import com.inqwise.opinion.common.OpinionEntityStatus;
import com.inqwise.opinion.common.Uid;
import com.inqwise.opinion.common.UidPrefixGenerator;

import io.vertx.sqlclient.templates.RowMapper;
import io.vertx.sqlclient.templates.TupleMapper;

class Mappers {
	public static final TupleMapper<AccountIdentity> IDENTITY = TupleMapper.mapper(identity -> {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("$account_id", identity.getId());
		return parameters;
	});
			
	public static final RowMapper<Account> ACCOUNT_ROW = row -> {
		var builder = Account.builder();
		builder.withId(row.getLong("account_id"))
		// product_id
		.withOwnerId(row.getLong("owner_id"))
		.withName(row.getString("account_name"))
		.withServicePackageId(row.getInteger("service_package_id"))
		// service_package_expiry_date
		.withStatus(row.getBoolean("is_active").booleanValue() ? OpinionEntityStatus.Active : OpinionEntityStatus.NonActive)
		// is_active
		.withCreatedAt(row.getLocalDateTime("insert_date"))
		//time_offset
		//max_users
		//min_deposit_amount
		//max_deposit_amount
		;
		
		return builder.build();
	};

	public static final TupleMapper<CreateRequest> CREATE_REQUEST = TupleMapper.mapper(request -> { 
			Map<String, Object> parameters = new HashMap<>();
			String uidPrefix = UidPrefixGenerator.SIZE_10.generate();
			parameters.put("$client_ip", request.getClientIp());
			parameters.put("$country_id", request.getCountryId());
			parameters.put("$account_name", request.getName());
			parameters.put("$uid_prefix", uidPrefix);
			parameters.put("$source_id", request.getSourceId());
			parameters.put("$product_id", request.getProductId());
			parameters.put("$service_package_id", request.getServicePackageId());
			parameters.put("$user_id", request.getUserId());
			parameters.put("$backoffice_user_id", request.getBackofficeUserId());
			return parameters;
	});

	public static final RowMapper<CreateResult> CREATE_RESULT_ROW = row -> {
		var builder = CreateResult.builder();
		builder.withUidToken(Uid.builder()
				.withId(row.getLong("account_id"))
				.withPrefix(row.getString("uid_prefix"))
				.build().toUidToken());
		return builder.build();
	};
}
