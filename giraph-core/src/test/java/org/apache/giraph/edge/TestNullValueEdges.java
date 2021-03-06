/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.giraph.edge;

import com.google.common.collect.Lists;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.apache.giraph.graph.TestVertexAndEdges.instantiateVertexEdges;
import static org.junit.Assert.assertEquals;

/**
 * Tests {@link VertexEdges} implementations with null edge values.
 */
public class TestNullValueEdges {
  /** {@link VertexEdges} classes to be tested. */
  private Collection<Class<? extends VertexEdges>>
      edgesClasses = Lists.newArrayList();

  @Before
  public void setUp() {
    edgesClasses.add(LongNullArrayEdges.class);
    edgesClasses.add(LongNullHashSetEdges.class);
  }

  @Test
  public void testEdges() {
    for (Class<? extends VertexEdges> edgesClass : edgesClasses) {
      testEdgesClass(edgesClass);
    }
  }

  private void testEdgesClass(
      Class<? extends VertexEdges> edgesClass) {
    VertexEdges<LongWritable, NullWritable> edges =
        (VertexEdges<LongWritable, NullWritable>)
            instantiateVertexEdges(edgesClass);

    List<Edge<LongWritable, NullWritable>> initialEdges = Lists.newArrayList(
        EdgeFactory.create(new LongWritable(1)),
        EdgeFactory.create(new LongWritable(2)),
        EdgeFactory.create(new LongWritable(3)));

    edges.initialize(initialEdges);
    assertEquals(3, edges.size());

    edges.add(EdgeFactory.createMutable(new LongWritable(4)));
    assertEquals(4, edges.size());

    edges.remove(new LongWritable(2));
    assertEquals(3, edges.size());
  }
}
