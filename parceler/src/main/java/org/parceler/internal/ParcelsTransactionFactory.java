/**
 * Copyright 2013 John Ericksen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.parceler.internal;

import com.sun.codemodel.JDefinedClass;
import org.androidtransfuse.adapter.ASTType;
import org.androidtransfuse.transaction.CodeGenerationScopedTransactionWorker;
import org.androidtransfuse.transaction.Transaction;
import org.androidtransfuse.transaction.TransactionFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import java.util.Map;

/**
 * @author John Ericksen
 */
public class ParcelsTransactionFactory implements TransactionFactory<Map<Provider<ASTType>, JDefinedClass>, Void> {

    private final Provider<CodeGenerationScopedTransactionWorker<Map<Provider<ASTType>, JDefinedClass>, Void>> workerProvider;
    private final ScopedTransactionFactory scopedTransactionFactory;

    @Inject
    public ParcelsTransactionFactory(
            @Named(ParcelerModule.PARCELS_TRANSACTION_WORKER)
            Provider<CodeGenerationScopedTransactionWorker<Map<Provider<ASTType>, JDefinedClass>, Void>> workerProvider,
            ScopedTransactionFactory scopedTransactionFactory) {
        this.workerProvider = workerProvider;
        this.scopedTransactionFactory = scopedTransactionFactory;
    }

    @Override
    public Transaction<Map<Provider<ASTType>, JDefinedClass>, Void> buildTransaction(Map<Provider<ASTType>, JDefinedClass> value) {
        return scopedTransactionFactory.buildTransaction(value, workerProvider);
    }
}
