package com.webgenerals.springbootc1.response;

/**
 * OperationStatusModel
 *
 * @author Florian Popa fpopa1991@gmail.com
 */
public class OperationStatusModel {

    private String operationResult;
    private String operationName;

    public String getOperationResult() {
        return operationResult;
    }

    public OperationStatusModel setOperationResult(String operationResult) {
        this.operationResult = operationResult;
        return this;
    }

    public String getOperationName() {
        return operationName;
    }

    public OperationStatusModel setOperationName(String operationName) {
        this.operationName = operationName;
        return this;
    }
}
