package com.rolex.alphax.slidingwindow.case3;

public enum HystrixRollingNumberEvent {
    SUCCESS(1), FAILURE(1), TIMEOUT(1), SHORT_CIRCUITED(1), THREAD_POOL_REJECTED(1), SEMAPHORE_REJECTED(1), BAD_REQUEST(1),
    FALLBACK_SUCCESS(1), FALLBACK_FAILURE(1), FALLBACK_REJECTION(1), FALLBACK_DISABLED(1), FALLBACK_MISSING(1), EXCEPTION_THROWN(1), COMMAND_MAX_ACTIVE(2), EMIT(1), FALLBACK_EMIT(1),
    THREAD_EXECUTION(1), THREAD_MAX_ACTIVE(2), COLLAPSED(1), RESPONSE_FROM_CACHE(1),
    COLLAPSER_REQUEST_BATCHED(1), COLLAPSER_BATCH(1);

    private final int type;

    private HystrixRollingNumberEvent(int type) {
        this.type = type;
    }

    public boolean isCounter() {
        return type == 1;
    }

    public boolean isMaxUpdater() {
        return type == 2;
    }

    public static HystrixRollingNumberEvent from(HystrixEventType eventType) {
        switch (eventType) {
            case BAD_REQUEST: return HystrixRollingNumberEvent.BAD_REQUEST;
            case COLLAPSED: return HystrixRollingNumberEvent.COLLAPSED;
            case EMIT: return HystrixRollingNumberEvent.EMIT;
            case EXCEPTION_THROWN: return HystrixRollingNumberEvent.EXCEPTION_THROWN;
            case FAILURE: return HystrixRollingNumberEvent.FAILURE;
            case FALLBACK_EMIT: return HystrixRollingNumberEvent.FALLBACK_EMIT;
            case FALLBACK_FAILURE: return HystrixRollingNumberEvent.FALLBACK_FAILURE;
            case FALLBACK_DISABLED: return HystrixRollingNumberEvent.FALLBACK_DISABLED;
            case FALLBACK_MISSING: return HystrixRollingNumberEvent.FALLBACK_MISSING;
            case FALLBACK_REJECTION: return HystrixRollingNumberEvent.FALLBACK_REJECTION;
            case FALLBACK_SUCCESS: return HystrixRollingNumberEvent.FALLBACK_SUCCESS;
            case RESPONSE_FROM_CACHE: return HystrixRollingNumberEvent.RESPONSE_FROM_CACHE;
            case SEMAPHORE_REJECTED: return HystrixRollingNumberEvent.SEMAPHORE_REJECTED;
            case SHORT_CIRCUITED: return HystrixRollingNumberEvent.SHORT_CIRCUITED;
            case SUCCESS: return HystrixRollingNumberEvent.SUCCESS;
            case THREAD_POOL_REJECTED: return HystrixRollingNumberEvent.THREAD_POOL_REJECTED;
            case TIMEOUT: return HystrixRollingNumberEvent.TIMEOUT;
            default: throw new RuntimeException("Unknown HystrixEventType : " + eventType);
        }
    }
}