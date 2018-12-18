package com.example.ysh.memong;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewWithCheckboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setTitle("dev2qa.com - Android ListView With CheckBox");

        // Get listview checkbox.
        final ListView listViewWithCheckbox = (ListView)findViewById(R.id.list_view_with_checkbox);

        // Initiate listview data.
        final List<ListViewItem> initItemList = this.getInitViewItemDtoList();

        // Create a custom list view adapter with checkbox control.
        final ListViewItemCheckboxBaseAdapter listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList);

        listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        listViewWithCheckbox.setAdapter(listViewDataAdapter);

        // When list view item is clicked.
        listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ListViewItem itemDto = (ListViewItem)itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if(itemDto.isChecked())
                {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                }else
                {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }

                //Toast.makeText(getApplicationContext(), "select item text : " + itemDto.getItemText(), Toast.LENGTH_SHORT).show();
            }
        });

        // Click this button to remove selected items from listview.
        Button selectRemoveButton = (Button)findViewById(R.id.deleteItem);
        selectRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(ListViewWithCheckboxActivity.this).create();
                alertDialog.setMessage("선택한 아이템을 지우시겠습니까?");

                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        int size = initItemList.size();
                        for(int i=0;i<size;i++)
                        {
                            ListViewItem dto = initItemList.get(i);

                            if(dto.isChecked())
                            {
                                initItemList.remove(i);
                                i--;
                                size = initItemList.size();
                            }
                        }

                        listViewDataAdapter.notifyDataSetChanged();
                    }
                });

                alertDialog.show();
            }
        });

    }


    // Return an initialize list of ListViewItemDTO.
    private List<ListViewItem> getInitViewItemDtoList()
    {
        String itemTextArr[] = {"계란", "버터", "라면", "고무장갑", "형광등"};

        List<ListViewItem> ret = new ArrayList<ListViewItem>();

        int length = itemTextArr.length;

        for(int i=0;i<length;i++)
        {
            String itemText = itemTextArr[i];

            ListViewItem dto = new ListViewItem();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }
}