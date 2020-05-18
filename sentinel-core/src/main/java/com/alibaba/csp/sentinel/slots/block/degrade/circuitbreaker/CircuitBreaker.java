/*
 * Copyright 1999-2019 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;

/**
 * Basic circuit breaker interface.
 *
 * @author Eric Zhao
 */
public interface CircuitBreaker {

    /**
     * Get the associated circuit breaking rule.
     *
     * @return associated circuit breaking rule
     */
    DegradeRule getRule();

    /**
     * Acquires permission of an invocation only if it is available at the time of invocation.
     *
     * @return {@code true} if permission was acquired and {@code false} otherwise
     */
    boolean tryPass();

    /**
     * Get current state of the circuit breaker.
     *
     * @return current state of the circuit breaker
     */
    State currentState();

    /**
     * Record a completed request with the given response time and error (if present) and
     * handle state transformation of the circuit breaker.
     *
     * @param rt the response time of this entry
     * @param error the error of this entry (if present)
     */
    void onRequestComplete(long rt, Throwable error);

    /**
     * Circuit breaker state.
     */
    enum State {
        /**
         * In {@code OPEN} state, all requests will be rejected until the next retry time point.
         */
        OPEN,
        HALF_OPEN,
        /**
         * In {@code CLOSED} state, all requests are permitted. When current metric value exceeds the threshold,
         * the circuit breaker will transform to {@code OPEN} state.
         */
        CLOSED
    }
}
