/*
 * Copyright 1999-2019 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
 * @author Eric Zhao
 * @since 1.8.0
 */
public interface CircuitBreakerStateChangeObserver {

    /**
     * Observer method triggered when circuit breaker state transformed to {@code CLOSED}.
     *
     * @param prev previous state of the circuit breaker
     * @param rule associated rule
     */
    void onTransformToClosed(CircuitBreaker.State prev, DegradeRule rule);

    /**
     * Observer method triggered when circuit breaker state transformed to {@code OPEN}.
     *
     * @param prev          previous state of the circuit breaker
     * @param rule          associated rule
     * @param snapshotValue triggered value on circuit breaker opens
     */
    void onTransformToOpen(CircuitBreaker.State prev, DegradeRule rule, double snapshotValue);

    /**
     * Observer method triggered when circuit breaker state transformed to {@code HALF_OPEN}.
     *
     * @param prev previous state of the circuit breaker
     * @param rule associated rule
     */
    void onTransformToHalfOpen(CircuitBreaker.State prev, DegradeRule rule);
}
