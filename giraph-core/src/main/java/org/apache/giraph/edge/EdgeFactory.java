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

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/**
 * Factory for creating Edges
 */
public class EdgeFactory {
  /** Do not construct */
  private EdgeFactory() { }

  /**
   * Create an edge pointing to a given ID with a value
   *
   * @param id target ID
   * @param value edge value
   * @param <I> Vertex ID type
   * @param <E> Edge Value type
   * @return Edge pointing to ID with value
   */
  public static <I extends WritableComparable,
                 E extends Writable>
  Edge<I, E> create(I id, E value) {
    return createMutable(id, value);
  }

  /**
   * Create an edge pointing to a given ID without a value
   *
   * @param id target ID
   * @param <I> Vertex ID type
   * @return Edge pointing to ID without a value
   */
  public static <I extends WritableComparable>
  Edge<I, NullWritable> create(I id) {
    return createMutable(id);
  }

  /**
   * Create a mutable edge pointing to a given ID with a value
   *
   * @param id target ID
   * @param value edge value
   * @param <I> Vertex ID type
   * @param <E> Edge Value type
   * @return Edge pointing to ID with value
   */
  public static <I extends WritableComparable,
                 E extends Writable>
  MutableEdge<I, E> createMutable(I id, E value) {
    if (value instanceof NullWritable) {
      return (MutableEdge<I, E>) createMutable(id);
    } else {
      return new DefaultEdge<I, E>(id, value);
    }
  }

  /**
   * Create a mutable edge pointing to a given ID with a value
   *
   * @param id target ID
   * @param <I> Vertex ID type
   * @return Edge pointing to ID with value
   */
  public static <I extends WritableComparable>
  MutableEdge<I, NullWritable> createMutable(I id) {
    return new EdgeNoValue<I>(id);
  }
}
