package com.example.krishiapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    ListView listView;

    int images[] = {R.drawable.apple,R.drawable.brinjal,R.drawable.grain,R.drawable.kiwi,R.drawable.orange};
    String names[] = {"Apple","Brinjal","Grain","Kiwi","Orange"};
    String desc[] = {"Rs 100","Rs 40","Rs 30","Rs 200","Rs 80"};
    String itemsfilter[] = {"fruits","veg","grain","fruits","fruits"};

    List<ItemsModel> listItems = new ArrayList<>();
    CustomAdapter customAdapter;
    String selectedFilter = "all";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listView = findViewById(R.id.listview);


        for(int i = 0; i < names.length; i++){

            ItemsModel itemsModel = new ItemsModel(names[i],desc[i],images[i],itemsfilter[i]);

            listItems.add(itemsModel);


        }

        customAdapter = new CustomAdapter(listItems,this);

        listView.setAdapter(customAdapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "You Selected " + names[i] + " as Item", Toast.LENGTH_SHORT).show();
                String str = names[i].toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                intent.putExtra("name",str);
                startActivity(intent);
                listView.setSelector(R.color.green);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

              customAdapter.getFilter().filter(newText);

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.search_view){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void filterList(String status)
    {
        selectedFilter = status;
        List<ItemsModel> filteredShapes = new ArrayList<>();
        for(ItemsModel itemsfilter: listItems)
        {
            if (itemsfilter.getItemsfilter().toLowerCase().contains(status))
            {
                filteredShapes.add(itemsfilter);

            }
        }
        customAdapter = new CustomAdapter(filteredShapes,this);

        listView.setAdapter(customAdapter);

    }

    public void allFilterTapped(View view) {
        selectedFilter = "all";
        customAdapter = new CustomAdapter(listItems,this);

        listView.setAdapter(customAdapter);
    }

    public void fruitsFilterTapped(View view) {

        filterList("fruits");
    }

    public void vegFilterTapped(View view) {

        filterList("veg");
    }

    public void grainFilterTapped(View view) {

        filterList("grain");
    }


    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<ItemsModel> itemsModelList;
        private List<ItemsModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<ItemsModel> itemsModelList, Context context) {
            this.itemsModelList = itemsModelList;
            this.itemsModelListFiltered = itemsModelList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {

            View view = getLayoutInflater().inflate(R.layout.row_items,null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView itemName = view.findViewById(R.id.itemName);
            TextView itemDesc = view.findViewById(R.id.itemDesc);

            imageView.setImageResource(itemsModelListFiltered.get(i).getImage());
            itemName.setText(itemsModelListFiltered.get(i).getName());
            itemDesc.setText(itemsModelListFiltered.get(i).getDesc());

            return view;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();


                    if(constraint == null || constraint.length() == 0){
                        filterResults.count = itemsModelList.size();
                        filterResults.values = itemsModelList;
                    }
                    else{
                        String searchStr = constraint.toString().toLowerCase();

                        List<ItemsModel> resultData = new ArrayList<>();

                        for(ItemsModel itemsModel: itemsModelList)
                           if(itemsModel.getName().toString().toLowerCase().contains(constraint.toString()) || itemsModel.getDesc().contains(searchStr)){
                               resultData.add(itemsModel);
                           }
                       filterResults.count = resultData.size();
                       filterResults.values = resultData;

                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    itemsModelListFiltered = (List<ItemsModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }
}