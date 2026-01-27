package com.inqwise.opinion.account;

/**
 * AccountOperationType.
 */
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

	/**
	 * getValue.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * getValueOrNullWhenUndefined.
	 */
	public Integer getValueOrNullWhenUndefined(){
		return Undefined.getValue() == value ? null : Integer.valueOf(value);
	}

	/**
	 * Constructs AccountOperationType.
	 */
	private AccountOperationType(int value) {
		this.value = value;
	}
	
	/**
	 * fromInt.
	 */
	public static AccountOperationType fromInt(Integer value){
		return fromInt(null == value ? 0 : value.intValue());
	}
	
	/**
	 * fromInt.
	 */
	public static AccountOperationType fromInt(int value){
		
		for (AccountOperationType a : AccountOperationType.values()) { 
			if (value == a.value) { 
	          return a; 
	        }
        } 
		
		return Undefined;
	}
}
