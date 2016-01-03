package com.esorokin.justweather.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Date: 22-Nov-15
 * Time: 17:14
 *
 * @author esorokin
 */
@SuppressWarnings("unused")
public abstract class CollectionRecycleAdapter<T> extends RecyclerView.Adapter<CollectionRecycleAdapter.RecycleViewHolder>
{
	protected LayoutInflater mInflater;
	private Context mContext;
	private final List<T> mList;

	public CollectionRecycleAdapter(Context context)
	{
		mContext = context.getApplicationContext();
		mInflater = LayoutInflater.from(context);
		mList = new ArrayList<>();
	}

	public CollectionRecycleAdapter(Context context, Collection<? extends T> collection)
	{
		this(context);

		if (collection != null)
		{
			mList.addAll(collection);
		}
	}

	/**
	 * Override if you need a special case.
	 *
	 * @param holder   Holder for element.
	 * @param position Position of element.
	 */
	@Override
	public void onBindViewHolder(RecycleViewHolder holder, int position)
	{
		//noinspection unchecked
		holder.bind(getItem(position));
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public int getItemCount()
	{
		return mList.size();
	}

	public T getItem(int position)
	{
		return mList.get(position);
	}

	protected String getString(int id)
	{
		return mContext.getString(id);
	}

	protected Context getContext()
	{
		return mContext;
	}

	public void setCollection(Collection<? extends T> collection)
	{
		mList.clear();

		if (collection != null)
		{
			mList.addAll(collection);
			notifyDataSetChanged();
		}
	}

	public void clearCollection()
	{
		if (mList != null)
		{
			int count = mList.size();
			mList.clear();
			notifyItemRangeRemoved(0, count);
		}
	}

	@SuppressWarnings("unused")
	public void removeItem(T item)
	{
		if (mList != null)
		{
			notifyItemRemoved(mList.indexOf(item));
			mList.remove(item);
		}
	}

	public List<T> getCollection()
	{
		return mList;
	}

	/**
	 * Abstract view holder for extending.
	 */
	public abstract static class RecycleViewHolder<T> extends RecyclerView.ViewHolder
	{
		public RecycleViewHolder(View itemView)
		{
			super(itemView);
			create(itemView);
		}

		protected abstract void create(View rootView);

		public abstract void bind(T model);

		final public View getRoot()
		{
			return itemView;
		}
	}
}
