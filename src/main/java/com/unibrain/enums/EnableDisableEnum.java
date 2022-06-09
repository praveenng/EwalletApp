package com.unibrain.enums;


public  enum EnableDisableEnum { enable('E',"Enabled"), disable('D',"Disabled"),blackList('B',"Blacklisted"),restore('R',"Restore");
		private Character enableDisableCharacterValue;  
		private String  enableDisableFullValue;  
		private EnableDisableEnum(Character enableDisableCharacterValue,String enableDisableFullValue){  
		this.enableDisableCharacterValue=enableDisableCharacterValue;  
		this.enableDisableFullValue=enableDisableFullValue;
		}  
		public Character getEnableDisableCharacterValue() {
	        return enableDisableCharacterValue;
	    }
		
		 
		public String getEnableDisableFullValue() {
	        return enableDisableFullValue;
	    }
		
		public static String getEnableDisableName(Character c){
			String enableDisableValue = null;
			EnableDisableEnum[] values = EnableDisableEnum.values();
			for (EnableDisableEnum enableDisableEnum : values) {
				if(enableDisableEnum.enableDisableCharacterValue.equals(c)){
					enableDisableValue = enableDisableEnum.getEnableDisableFullValue();
				}
			}
			return enableDisableValue;
			
		}
		
	} 	
	
