/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.rocketmq.store;

import java.nio.ByteBuffer;
import java.util.Map;

public interface MessageFilter {
    /**
     * match by tags code or filter bit map which is calculated when message received
     * and stored in consume queue ext.
     * 通过标签代码或过滤器位图匹配，这是在接收到消息并存储在消费队列 ext 中时计算的。
     *
     * @param tagsCode tagsCode
     * @param cqExtUnit extend unit of consume queue
     */
    boolean isMatchedByConsumeQueue(final Long tagsCode,
        final ConsumeQueueExt.CqExtUnit cqExtUnit);

    /**
     * match by message content which are stored in commit log.
     * 按存储在提交日志中的消息内容匹配。
     *
     * <br>{@code msgBuffer} and {@code properties} are not all null.If invoked in store,
     * {@code properties} is null;If invoked in {@code PullRequestHoldService}, {@code msgBuffer} is null.
     *
     * @param msgBuffer message buffer in commit log, may be null if not invoked in store.
     * @param properties message properties, should decode from buffer if null by yourself.
     */
    boolean isMatchedByCommitLog(final ByteBuffer msgBuffer,
        final Map<String, String> properties);
}
