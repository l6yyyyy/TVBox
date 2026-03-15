package com.example.tvbox;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.app.SearchSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.search_fragment, new SearchFragment())
                    .commitNow();
        }
    }

    public static class SearchFragment extends SearchSupportFragment {

        private ArrayObjectAdapter mRowsAdapter;

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            setupUIElements();
            setupEventListeners();
        }

        private void setupUIElements() {
            setSearchHint(getString(R.string.msg_search_placeholder));
            setBadgeDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        }

        private void setupEventListeners() {
            setOnItemViewClickedListener(new OnItemViewClickedListener() {
                @Override
                public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                    if (item instanceof Movie) {
                        Movie movie = (Movie) item;
                        // 跳转到详情页
                    }
                }
            });

            setOnItemViewSelectedListener(new OnItemViewSelectedListener() {
                @Override
                public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                    if (item instanceof Movie) {
                        Movie movie = (Movie) item;
                        // 更新详情信息
                    }
                }
            });

            setOnSearchQuerySubmittedListener(new SearchSupportFragment.OnSearchQuerySubmittedListener() {
                @Override
                public void onSearchQuerySubmitted(String query) {
                    // 处理搜索查询
                    loadSearchResults(query);
                }
            });
        }

        private void loadSearchResults(String query) {
            mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

            // 模拟搜索结果
            ArrayObjectAdapter searchAdapter = new ArrayObjectAdapter(new CardPresenter());
            searchAdapter.add(new Movie(query + " 电影1", "https://example.com/poster1.jpg", "2023", "动作", "导演1", "演员1, 演员2", "剧情简介1"));
            searchAdapter.add(new Movie(query + " 电影2", "https://example.com/poster2.jpg", "2023", "喜剧", "导演2", "演员3, 演员4", "剧情简介2"));
            searchAdapter.add(new Movie(query + " 电影3", "https://example.com/poster3.jpg", "2023", "科幻", "导演3", "演员5, 演员6", "剧情简介3"));
            HeaderItem searchHeader = new HeaderItem(0, "搜索结果");
            mRowsAdapter.add(new ListRow(searchHeader, searchAdapter));

            setAdapter(mRowsAdapter);
        }
    }
}
