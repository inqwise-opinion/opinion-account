package com.inqwise.opinion.account;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.inqwise.opinion.common.OpinionEntityStatus;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.VertxExtension;

@ExtendWith(VertxExtension.class)
class AccountTest {
	@BeforeEach
	void setUp(Vertx vertx) throws Exception {
	}

	@Test
	void fromJson_populatesAllFields() {
		var createdAt = LocalDateTime.of(2024, 2, 25, 11, 30, 45);
		var updatedAt = createdAt.plusHours(3);
		var lastCreditedAt = LocalDate.of(2024, 2, 26);
		var json = new JsonObject()
			.put(Account.Keys.UID, "account-uid")
			.put(Account.Keys.ID, 72L)
			.put(Account.Keys.SERVICE_PACKAGE_ID, 5)
			.put(Account.Keys.NAME, "Sample Account")
			.put(Account.Keys.OWNER_ID, 99L)
			.put(Account.Keys.TIMEZONE, "UTC")
			.put(Account.Keys.CREATED_AT, createdAt.toString())
			.put(Account.Keys.UPDATED_AT, updatedAt.toString())
			.put(Account.Keys.STATUS_ID, OpinionEntityStatus.Active.value())
			.put(Account.Keys.BALANCE, 1200L)
			.put(Account.Keys.SUPPLY_DAYS_INTERVAL, 7)
			.put(Account.Keys.NEXT_SUPPLY_SESSION_SCREDIT, 40)
			.put(Account.Keys.LAST_SESSIONS_CREDITED_AT, lastCreditedAt.toString());
		var account = new Account(json);

		Assertions.assertEquals("account-uid", account.getUid());
		Assertions.assertEquals(72L, account.getId());
		Assertions.assertEquals(5, account.getServicePackageId());
		Assertions.assertEquals("Sample Account", account.getName());
		Assertions.assertEquals(99L, account.getOwnerId());
		Assertions.assertEquals("UTC", account.getTimezone());
		Assertions.assertEquals(createdAt, account.getCreatedAt());
		Assertions.assertEquals(updatedAt, account.getUpdatedAt());
		Assertions.assertEquals(OpinionEntityStatus.Active, account.getStatus());
		Assertions.assertEquals(1200L, account.getBalance());
		Assertions.assertEquals(7, account.getSupplyDaysInterval());
		Assertions.assertEquals(40, account.getNextSupplySessionsCredit());
		Assertions.assertEquals(lastCreditedAt, account.getLastSessionsCreditedAt());
	}

	@Test
	void toJson_includesOnlyInitializedFields() {
		var createdAt = LocalDateTime.of(2024, 3, 1, 9, 15, 0);
		var lastCreditedAt = LocalDate.of(2024, 3, 2);
		var account = Account.builder()
			.withUid("uid-123")
			.withId(91L)
			.withName("Another Account")
			.withOwnerId(101L)
			.withCreatedAt(createdAt)
			.withStatus(OpinionEntityStatus.Active)
			.withBalance(4500L)
			.withNextSupplySessionsCredit(60)
			.withLastSessionsCreditedAt(lastCreditedAt)
			.build();
		var json = account.toJson();

		Assertions.assertEquals(91L, json.getLong(Account.Keys.ID));
		Assertions.assertEquals("uid-123", json.getString(Account.Keys.UID));
		Assertions.assertEquals("Another Account", json.getString(Account.Keys.NAME));
		Assertions.assertEquals(101L, json.getLong(Account.Keys.OWNER_ID));
		Assertions.assertEquals(createdAt.toString(), json.getString(Account.Keys.CREATED_AT));
		Assertions.assertEquals(OpinionEntityStatus.Active.value(), json.getInteger(Account.Keys.STATUS_ID));
		Assertions.assertEquals(4500L, json.getLong(Account.Keys.BALANCE));
		Assertions.assertEquals(60, json.getInteger(Account.Keys.NEXT_SUPPLY_SESSION_SCREDIT));
		Assertions.assertEquals(lastCreditedAt.toString(), json.getString(Account.Keys.LAST_SESSIONS_CREDITED_AT));
		Assertions.assertFalse(json.containsKey(Account.Keys.SERVICE_PACKAGE_ID));
		Assertions.assertFalse(json.containsKey(Account.Keys.TIMEZONE));
	}

	@Test
	void builderFrom_createsIndependentCopy() {
		var createdAt = LocalDateTime.of(2024, 4, 1, 8, 0);
		var updatedAt = createdAt.plusDays(2);
		var lastCreditedAt = LocalDate.of(2024, 4, 3);
		var original = Account.builder()
			.withUid("base-uid")
			.withId(11L)
			.withServicePackageId(3)
			.withName("Original")
			.withOwnerId(88L)
			.withTimezone("PST")
			.withCreatedAt(createdAt)
			.withUpdatedAt(updatedAt)
			.withStatus(OpinionEntityStatus.Active)
			.withBalance(2500L)
			.withSupplyDaysInterval(5)
			.withNextSupplySessionsCredit(30)
			.withLastSessionsCreditedAt(lastCreditedAt)
			.build();
		var copy = Account.builderFrom(original)
			.withName("Updated")
			.build();

		Assertions.assertNotSame(original, copy);
		Assertions.assertEquals("Updated", copy.getName());
		Assertions.assertEquals(original.getUid(), copy.getUid());
		Assertions.assertEquals(original.getId(), copy.getId());
		Assertions.assertEquals(original.getServicePackageId(), copy.getServicePackageId());
		Assertions.assertEquals(original.getOwnerId(), copy.getOwnerId());
		Assertions.assertEquals(original.getTimezone(), copy.getTimezone());
		Assertions.assertEquals(original.getCreatedAt(), copy.getCreatedAt());
		Assertions.assertEquals(original.getUpdatedAt(), copy.getUpdatedAt());
		Assertions.assertEquals(original.getStatus(), copy.getStatus());
		Assertions.assertEquals(original.getBalance(), copy.getBalance());
		Assertions.assertEquals(original.getSupplyDaysInterval(), copy.getSupplyDaysInterval());
		Assertions.assertEquals(original.getNextSupplySessionsCredit(), copy.getNextSupplySessionsCredit());
		Assertions.assertEquals(original.getLastSessionsCreditedAt(), copy.getLastSessionsCreditedAt());
		Assertions.assertEquals("Original", original.getName());
	}
}
