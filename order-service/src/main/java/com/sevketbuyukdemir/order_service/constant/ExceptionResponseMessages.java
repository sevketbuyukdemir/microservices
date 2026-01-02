package com.sevketbuyukdemir.order_service.constant;

public enum ExceptionResponseMessages {
    GENERIC_ERROR {
        public String toString() {
            return "Internal server error.";
        }
    },
    BAD_REQUEST {
        public String toString() {
            return "Bad request.";
        }
    },
    SQL_EXCEPTION {
        public String toString() {
            return "Persistence layer related error. Please try again later.";
        }
    },
    REJECTED_EXECUTION_EXCEPTION {
        public String toString() {
            return "Bandwidth limit exceeded. Please try again later.";
        }
    }
}
