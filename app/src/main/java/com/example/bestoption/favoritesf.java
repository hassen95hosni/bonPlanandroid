package com.example.bestoption;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.bestoption.ADAPTERS.MyAdapter;
import com.example.bestoption.entity.Category;
import com.example.bestoption.entity.City;
import com.example.bestoption.entity.Plans;
import com.example.bestoption.interfaces.OnItemClickListener;
import com.example.bestoption.interfaces.PlanInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link favoritesf.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link favoritesf#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favoritesf extends Fragment implements OnItemClickListener {
    private List<Plans> plans;
    OnItemClickListener onItemClickListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  //  private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    private  static Retrofit retrofit = null;
    public static final String BASE_URL= "https://192.168.43.227:1330/";


    // TODO: Rename and change types of parameters
   // private String mParam1;
   // private String mParam2;

    private OnFragmentInteractionListener mListener;

    public favoritesf() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favoritesf.
     */
    // TODO: Rename and change types and number of parameters
    public static favoritesf newInstance(String param1, String param2) {
        favoritesf fragment = new favoritesf();
        Bundle args = new Bundle();
     //   args.putString(ARG_PARAM1, param1);
      //  args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favoritesf, container, false);
        getAllPlansofline(v);
        return v;
    }

    @Override
    public void onItemClick(int position) {
        plans.get(position);
        startActivity(new Intent(getActivity(),details.class));

    }


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



    private  int dpTopx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private List<Plans> getAllPlans(View view){
        final RecyclerView recyclerview ;
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        recyclerview = (RecyclerView) view.findViewById(R.id.favoritesrecycler);
        List<Plans> list= new ArrayList<>() ;
        // list= Arrays.asList("hh","hh","yes");
/*
        for (int i=1 ; i<5;i++){
            list.add("article "+i);
        }
        list.add("hassen");
  */    //  list.addAll(getAllPlans());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerview.addItemDecoration(new favoritesf.GridSpacing(2, dpTopx(10) ,true));
        recyclerview.setLayoutManager(layoutManager);



        final List<Plans> plans = new ArrayList<Plans>();
        PlanInterface planInterface= retrofit.create(PlanInterface.class);
        SharedPreferences sharedPreferences =  this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        sharedPreferences.getString("login","false");
        if(sharedPreferences.getString("login","false").equals("false")){
            Call<List<Plans>> calls = planInterface.getallnonauth();
            calls.enqueue(new Callback<List<Plans>>() {
               @Override
                public void onResponse(Call<List<Plans>> call, Response<List<Plans>> response) {
                    Toast.makeText(getContext(),"success",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                    for(int i =0 ;i<response.body().size();i++){
                        Toast.makeText(getContext(),response.body().get(i).getName(),Toast.LENGTH_SHORT).show();
                        RecyclerView.Adapter madapter = new MyAdapter(response.body(),onItemClickListener);
                        recyclerview.setAdapter(madapter);
                    }
                    plans.addAll(response.body());

                }

                @Override
                public void onFailure(Call<List<Plans>> call, Throwable t) {
                    Toast.makeText(getContext(),"failure",Toast.LENGTH_SHORT).show();

                }
            });

        }
        else {
            Call<List<Plans>> call = planInterface.getall();
            call.enqueue(new Callback<List<Plans>>() {
                @Override
                public void onResponse(Call<List<Plans>> call, Response<List<Plans>> response) {
                    //Toast.makeText(getContext(),"success",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                    for(int i =0 ;i<response.body().size();i++){
                        Toast.makeText(getContext(),response.body().get(i).getName(),Toast.LENGTH_SHORT).show();
                        RecyclerView.Adapter madapter = new MyAdapter(response.body(),onItemClickListener);
                        recyclerview.setAdapter(madapter);
                    }
                    plans.addAll(response.body());
                }

                @Override
                public void onFailure(Call<List<Plans>> call, Throwable t) {
                    Toast.makeText(getContext(),"failure",Toast.LENGTH_SHORT).show();

                }
            });

        }
        return plans;
    }




    private List<Plans> getAllPlansofline(View view){
        final RecyclerView recyclerview ;
        recyclerview = (RecyclerView) view.findViewById(R.id.favoritesrecycler);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerview.addItemDecoration(new favoritesf.GridSpacing(2, dpTopx(10) ,true));
        recyclerview.setLayoutManager(layoutManager);
        plans = new ArrayList<Plans>();
        Plans plans1 = new Plans();
        plans1.setName("the diesel");
        Category category = new Category();
        category.setName("restaurent");
        plans1.setCategory(category);
        City city = new City();
        city.setName("sousse");
        plans1.setCity(city);
        plans1.setDescriptionCourt("the diesel restaurent provides you with the most delicious meals");
        plans1.setDescriptionLong("with its variouty of sidhes the dissiel will provide with whatever food you may desire with delecious beverage");
        plans.add(plans1);

        RecyclerView.Adapter madapterr = new MyAdapter(plans,this);
        recyclerview.setAdapter(madapterr);

       SharedPreferences sharedPreferences =  this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        sharedPreferences.getString("login","false");
        if(sharedPreferences.getString("login","false").equals("false")){
            RecyclerView.Adapter madapter = new MyAdapter(plans,this);
            recyclerview.setAdapter(madapter);
        }

        else {
            RecyclerView.Adapter madapter = new MyAdapter(plans,this);
            recyclerview.setAdapter(madapter);
        }



        return plans;
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
