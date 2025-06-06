/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.parquet.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.parquet.io.api.GroupConverter;
import org.apache.parquet.io.api.RecordMaterializer;
import org.apache.parquet.schema.MessageType;

class AvroRecordMaterializer<T> extends RecordMaterializer<T> {

  private AvroRecordConverter<T> root;

  AvroRecordMaterializer(
      MessageType requestedSchema, Schema avroSchema, GenericData baseModel, ReflectClassValidator validator) {
    this.root = new AvroRecordConverter<T>(requestedSchema, avroSchema, baseModel, validator);
  }

  @Override
  public T getCurrentRecord() {
    return root.getCurrentRecord();
  }

  @Override
  public GroupConverter getRootConverter() {
    return root;
  }
}
