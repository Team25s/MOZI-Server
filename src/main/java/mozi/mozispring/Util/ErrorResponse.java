package mozi.mozispring.Util;

public class ErrorResponse extends BasicResponse{
    private String message;
    public ErrorResponse(String message) {
        this.message = message;
    }
}

