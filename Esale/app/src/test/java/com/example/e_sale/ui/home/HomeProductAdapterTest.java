package com.example.e_sale.ui.home;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_sale.R;
import com.example.e_sale.ui.Show.HomeShowFragment;
import com.example.e_sale.ui.home.HomeProduct;
import com.example.e_sale.ui.home.HomeProductAdapter;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class HomeProductAdapterTest {

    private HomeProductAdapter adapter;
    private List<HomeProduct> productList;

    @Before
    public void setUp() {
        HomeProduct product1 = new HomeProduct("Product 1", "Description 1", "http://example.com/photo1.jpg");
        HomeProduct product2 = new HomeProduct("Product 2", "Description 2", "http://example.com/photo2.jpg");
        productList = Arrays.asList(product1, product2);

        // FragmentManager mock
        FragmentManager fragmentManager = mock(FragmentManager.class);
        adapter = new HomeProductAdapter(productList, fragmentManager);
    }

    @Test
    public void testAdapterItemCount() {
        assertEquals(2, adapter.getItemCount());
    }
}
