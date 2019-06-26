package com.example.bestoption;

import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bestoption.ADAPTERS.MessageAdapter;
import com.example.bestoption.ADAPTERS.MyAdapter;
import com.example.bestoption.design.customfonts.MyEditText;
import com.example.bestoption.entity.Message;
import com.example.bestoption.entity.Plans;
import com.example.bestoption.interfaces.MessageInterface;
import com.example.bestoption.interfaces.OnItemClickListener;
import com.example.bestoption.interfaces.PlanInterface;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Messenger extends AppCompatActivity implements OnItemClickListener{
    private  static Retrofit retrofit = null;
    public static final String BASE_URL= "http://192.168.43.227:1330/";
public List<Message>messages;
    EditText messenger;


    public void send(View view){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MessageInterface messageInterface= retrofit.create(MessageInterface.class);
        Message message = new Message();
        message.setMessage(messenger.getText().toString());
        Call<String> call = messageInterface.addOne(message.getMessage());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sub (MqttAndroidClient client){
        String topic = "message";
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // The message was published
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // The subscription could not be performed, maybe the user was not
                    // authorized to subscribe on the specified topic e.g. using wildcards

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        getAlloffline();
       // messenger = findViewById(R.id.messenger);
     //   getAll();
      /*  String clientId = MqttClient.generateClientId();
        MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://broker.hivemq.com:1883",
                        clientId);

        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                   // Log.d(TAG, "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    //Log.d(TAG, "onFailure");

                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        try {
            IMqttToken token = client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        */
    }
public void addMessage(View view){
    MyEditText newMessage=(com.example.bestoption.design.customfonts.MyEditText)findViewById(R.id.newMessage);
    Message message = new Message();
    message.setMessage(newMessage.getText().toString());
    message.setMe(true);
    final RecyclerView recyclerview ;
    recyclerview = (RecyclerView) findViewById(R.id.messengerrecycler);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerview.setLayoutManager(layoutManager);


    messages.add(message);
    RecyclerView.Adapter madapter = new MessageAdapter(messages,this);
    recyclerview.setAdapter(madapter);
newMessage.setText("");
    }

    private  int dpTopx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }
    // TODO: Rename method, update argument and hook method into UI event
    private List<Message> getAll(){
      //  final RecyclerView recyclerview ;
      //  if (retrofit==null){
        //    retrofit = new Retrofit.Builder()
       //             .baseUrl(BASE_URL)
      //              .addConverterFactory(GsonConverterFactory.create())
     //               .build();
    //    }
  //      recyclerview = (RecyclerView) findViewById(R.id.recycler);
  //      List<Message> list= new ArrayList<Message>() ;
        // list= Arrays.asList("hh","hh","yes");
/*
        for (int i=1 ; i<5;i++){
            list.add("article "+i);
        }
        list.add("hassen");
  */    //  list.addAll(getAllPlans());
     //   RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
     //   recyclerview.addItemDecoration(new Messenger.GridSpacing(2, dpTopx(10) ,true));
     //   recyclerview.setLayoutManager(layoutManager);



        final List<Message> plans = new ArrayList<Message>();
     //   MessageInterface messageInterface= retrofit.create(MessageInterface.class);
     //   Call<List<Message>> call = messageInterface.getall();
      //  call.enqueue(new Callback<List<Message>>() {
      /*      @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_SHORT).show();
                for(int i =0 ;i<response.body().size();i++){
                    Toast.makeText(getApplicationContext(),response.body().get(i).getMessage(),Toast.LENGTH_SHORT).show();
                    RecyclerView.Adapter madapter = new MessageAdapter(response.body());
                    recyclerview.setAdapter(madapter);
                }
                plans.addAll(response.body());

            }
*/
 /*           @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_SHORT).show();

            }
        });
   */     return plans;
    }




    private List<Message> getAlloffline(){
        final RecyclerView recyclerview ;
        recyclerview = (RecyclerView) findViewById(R.id.messengerrecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(layoutManager);
        messages = new ArrayList<Message>();
        Message message = new Message();
        message.setId(1);
        message.setMessage("hello");
        message.setMe(true);
        messages.add(message);
        message.setId(2);
        message.setMessage("hi");
        message.setMe(false);
        messages.add(message);
        message.setId(3);
        message.setMessage("how are you ?");
        message.setMe(true);
        messages.add(message);
        message.setId(4);
        message.setMessage("fine and you ?");
        message.setMe(false);
        messages.add(message);
        message.setId(1);
        message.setMessage("me too");
        message.setMe(true);

        messages.add(message);
                    RecyclerView.Adapter madapter = new MessageAdapter(messages,this);
                    recyclerview.setAdapter(madapter);
        return messages;
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getApplicationContext(),"under construction",Toast.LENGTH_SHORT).show();
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
