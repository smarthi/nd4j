/*-
 *
 *  * Copyright 2015 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 *
 */

package org.nd4j.linalg.api.ops;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * An accumulation is an op that given:<br>
 * x -> the origin ndarray<br>
 * y -> the pairwise ndarray<br>
 * n -> the number of times to accumulate<br>
 * <p/>
 * <p/>
 * Of note here in the extra arguments.
 * <p/>
 * An accumulation (or reduction in some terminology)
 * has a concept of a starting value.
 * <p/>
 * The starting value is the initialization of the solution
 * to the operation.
 * <p/>
 * An accumulation should always have the extraArgs()
 * contain the zero value as the first value.
 * <p/>
 * This allows the architecture to generalize to different backends
 * and gives the implementer of a backend a way of hooking in to
 * passing parameters to different engines.<br>
 *
 * Note that Accumulation op implementations should be stateless
 * (other than the final result and x/y/z/n arguments) and hence threadsafe,
 * such that they may be parallelized using the update, combineSubResults and
 * set/getFinalResults methods.
 * @author Adam Gibson
 */
public interface Accumulation extends Op {

    /**
     * Returns the no op version
     * of the input
     * Basically when a reduce can't happen (eg: sum(0) on a row vector)
     * you have a no op state for a given reduction.
     * For most accumulations, this should return x
     * but certain transformations should return say: the absolute value
     *
     *
     * @return the no op version of the input
     */
    INDArray noOp();
    /** Get the final result (may return null if getAndSetFinalResult has not
     * been called, or for accumulation ops on complex arrays)
     */
    Number getFinalResult();

    /** Get the final result (may return null if getAndSetFinalResult has not
     * been called, or for accumulation ops on complex arrays)
     */
    void setFinalResult(double value);


    /**Initial value (used to initialize the accumulation op)
     * @return the initial value
     */
    double zeroDouble();

    /** Initial value (used to initialize the accumulation op) */
    float zeroFloat();

    /**
     * Initial value for half
     * @return
     */
    float zeroHalf();

    boolean isComplexAccumulation();

}
