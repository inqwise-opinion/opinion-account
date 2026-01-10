package com.inqwise.opinion.account;

import java.util.HashMap;
import java.util.Map;

import com.inqwise.opinion.common.OpinionEntityStatus;

import io.vertx.sqlclient.templates.RowMapper;
import io.vertx.sqlclient.templates.TupleMapper;

class Mappers {
	public static final TupleMapper<AccountIdentity> ACCOUNT_IDENTITY = 
			TupleMapper.mapper(identity -> {
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("$account_id", identity.getId());
				return parameters;
			});
			
	public static final RowMapper<Account> ACCOUNT_ROW = 
			row -> {
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
}
