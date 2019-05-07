package com.example.bestoption;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bestoption.ADAPTERS.CategoriesAdapter;
import com.example.bestoption.ADAPTERS.MyAdapter;
import com.example.bestoption.entity.Category;
import com.example.bestoption.entity.Plans;
import com.example.bestoption.interfaces.CategoryInterface;
import com.example.bestoption.interfaces.PlanInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class categoriesf extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   // private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
   // private String mParam1;
   // private String mParam2;

    private OnFragmentInteractionListener mListener;
    private  static Retrofit retrofit = null;
    public static final String BASE_URL= "http://192.168.43.227:1330/";

    public categoriesf() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static categoriesf newInstance(String param1, String param2) {
        categoriesf fragment = new categoriesf();
        Bundle args = new Bundle();
     //   args.putString(ARG_PARAM1, param1);
      //  args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_categoriesf, container, false);
        getAll(view);
        return view;
    }

    private  int dpTopx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }
    private List<Category> getAll(View view){
        final RecyclerView recyclerview ;
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclercatgories);
        List<Category> list= new ArrayList<Category>() ;
        // list= Arrays.asList("hh","hh","yes");
/*
        for (int i=1 ; i<5;i++){
            list.add("article "+i);
        }
        list.add("hassen");
  */    //  list.addAll(getAllPlans());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerview.addItemDecoration(new categoriesf.GridSpacing(2, dpTopx(10) ,true));
        recyclerview.setLayoutManager(layoutManager);



        final List<Category> plans = new ArrayList<Category>();
        CategoryInterface categoryInterface= retrofit.create(CategoryInterface.class);
        Call<List<Category>> call = categoryInterface.getall();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Toast.makeText(getContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                for(int i =0 ;i<response.body().size();i++){
                    Toast.makeText(getContext(),response.body().get(i).getName(),Toast.LENGTH_SHORT).show();
                    RecyclerView.Adapter madapter = new CategoriesAdapter(response.body());
                    recyclerview.setAdapter(madapter);
                }
                plans.addAll(response.body());

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

                Toast.makeText(getContext(),"failure",Toast.LENGTH_SHORT).show();

            }
        });
        return plans;
    }







    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public class GridSpacing extends RecyclerView.ItemDecoration{
        private int count ;
        private int spacing ;
        private boolean includegde;


        public GridSpacing(int count, int spacing, boolean includegde) {
            this.count = count;
            this.spacing = spacing;
            this.includegde = includegde;
        }

        @Override
        public void getItemOffsets(Rect outrect , View view , RecyclerView parent , RecyclerView.State state){
            int position = parent.getChildAdapterPosition(view);
            int column = position % count;

            if (includegde){
                outrect.left= spacing  - column *spacing /count ;
                outrect.right = (column+1)*spacing/count ;
                if (position<count){
                    outrect.top= spacing;
                }
                outrect.bottom=spacing;
            }else {
                outrect.left= spacing  - column *spacing /count ;
                outrect.right = (column+1)*spacing/count ;
                if (position>=count){
                    outrect.top= spacing;
                }

            }
        }
    }
}
