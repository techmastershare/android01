package com.example.shareimageapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TransactionModel {
	private ArrayList<Transaction> mTransactionList = new ArrayList<Transaction>();

	public void add(final Transaction transaction) {
		mTransactionList.add(transaction);
	}

	public Transaction get(final int index) {
		if (index < 0)
			return null;
		return mTransactionList.get(index);
	}

	public Transaction getById(long transactionId) {
		for (int i = 0; i < count(); i++) {
			Transaction transaction = get(i);
			if (transaction != null
					&& transaction.getTransactionId() == transactionId) {
				return transaction;
			}
		}

		return null;
	}

	protected boolean remove(Transaction transaction) {
		return mTransactionList.remove(transaction);
	}

	public boolean remove(long transactionId) {
		for (int i = 0; i < count(); i++) {
			Transaction transaction = get(i);
			if (transaction != null
					&& transaction.getTransactionId() == transactionId) {
				remove(transaction);
				return true;
			}
		}

		return false;
	}

	public boolean update(Transaction newTransaction) {
		long transactionId = newTransaction.getTransactionId();
		for (int i = 0; i < count(); i++) {
			Transaction transaction = get(i);
			if (transaction != null
					&& transaction.getTransactionId() == transactionId) {
				transaction.copy(newTransaction);
				return true;
			}
		}

		return false;
	}

	public int count() {
		return mTransactionList.size();
	}

	public void clear() {
		mTransactionList.clear();
	}
}
