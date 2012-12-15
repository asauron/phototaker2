package com.example.phototaker2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TakeTutorial extends ListActivity {
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    String[] values = new String[] { "Where to find the bees", "Collecting Stranded Honey bees", "Constructing a light trap",
        "Storing your samples", "Upload your samples"};
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, values);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
   // String item = (String) getListAdapter().getItem(position);
	  String item = (String) getListAdapter().getItem(position);
	    if (item.equals("Where to find the bees"))
	    {
	      Intent intent = new Intent(this, FirstPage.class);
	      startActivity(intent);
	      
	    }
	   else if (item.equals("Collecting Stranded Honey bees"))
	    {
		      Intent intent = new Intent(this, SecondPage.class);
		      startActivity(intent);
		      
		    }
	   else if (item.equals("Constructing a light trap"))
	    {
		      Intent intent = new Intent(this, ThirdPage.class);
		      startActivity(intent);
		      
		    }
	   else if (item.equals("Storing your samples"))
	    {
		      Intent intent = new Intent(this, FourthPage.class);
		      startActivity(intent);
		      
		    }
	   else if (item.equals("Upload your samples"))
	    {
		      Intent intent = new Intent(this, FifthPage.class);
		      startActivity(intent);
		      
		    }
    
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
  }
} 

