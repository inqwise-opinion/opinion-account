package com.inqwise.opinion.account;

public enum AccountOperationType {
	Undefined(0),
	StartBalance(1),
	Fund(2),
	ChargePay(3),
	Credit(4),
	Debit(5), 
	Refund(6),
	CancelCharge(7),
	InvoiceOpen(8);
	
	private final int value;

	public int getValue() {
		return value;
	}
	
	public Integer getValueOrNullWhenUndefined(){
		return Undefined.getValue() == value ? null : Integer.valueOf(value);
	}

	private AccountOperationType(int value) {
		this.value = value;
	}
	
	public static AccountOperationType fromInt(Integer value){
		return fromInt(null == value ? 0 : value.intValue());
	}
	
	public static AccountOperationType fromInt(int value){
		
		for (AccountOperationType a : AccountOperationType.values()) { 
			if (value == a.value) { 
	          return a; 
	        }
        } 
		
		return Undefined;
	}
}
