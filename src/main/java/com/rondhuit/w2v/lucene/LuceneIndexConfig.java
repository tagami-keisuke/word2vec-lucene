/*
 *  Copyright (c) 2014 RONDHUIT Co.,Ltd.
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.rondhuit.w2v.lucene;

import org.apache.lucene.store.Directory;

import com.rondhuit.w2v.Config;

public class LuceneIndexConfig extends Config {

  static final String DEF_ANALYZER     = "org.apache.lucene.analysis.standard.StandardAnalyzer";
  
  private String analyzer = DEF_ANALYZER;
  private String field;
  private Directory indexDir = null;

  public LuceneIndexConfig setIndexDir(Directory indexDir){
    this.indexDir = indexDir;
    return this;
  }
  
  public Directory getIndexDir(){
    return indexDir;
  }

  public LuceneIndexConfig setField(String field){
    this.field = field;
    return this;
  }
  
  public String getField(){
    return field;
  }

  public LuceneIndexConfig setAnalyzer(String analyzer){
    this.analyzer = analyzer;
    return this;
  }
  
  public String getAnalyzer(){
    return analyzer;
  }
}
