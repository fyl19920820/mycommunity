package cn.fengylb.mycommunity.mycommunity.exception;

public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;
    public CustomizeException(String message){
        super(message);
    }
    public CustomizeException(ICustomizeErrorCode customizeErrorCode){
        super(customizeErrorCode.getMessage());
        this.code = customizeErrorCode.getCode();
        this.message = customizeErrorCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }

}
