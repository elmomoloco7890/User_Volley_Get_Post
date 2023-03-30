package prime.projects.user_volley_get_post.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import prime.projects.user_volley_get_post.R
import prime.projects.user_volley_get_post.adapter.HotlineAdapter
import prime.projects.user_volley_get_post.data.Hotlines
import prime.projects.user_volley_get_post.databinding.FragmentMessageBinding


class MessageFragment : Fragment(), MessageItemClickListener {

    //the binding variable
    private var binding: FragmentMessageBinding ?= null

    //the hotline list variable
    private var hotlineList: ArrayList<Hotlines> = ArrayList()

    //the adapter variable
    private lateinit var adapter: HotlineAdapter

    private lateinit var queue: RequestQueue

    private val url: String = "https://www.freeformatter.com/json-formatter.html"

    /* private val logUrl: String = "the phone interaction" */


    //this fragment is where the request happens
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val messageBinding = FragmentMessageBinding.inflate(inflater, container, false)
        binding = messageBinding
        return messageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HotlineAdapter(hotlineList, this)
        binding?.apply {
            messageFragment = this@MessageFragment
            hotAdapter = adapter
        }
        //the hotline list array


        queue = Volley.newRequestQueue(requireActivity())
        getHotlines()
        postHotLines()
        //switching from just textview to recyclerview

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getHotlines() {

        val jsonArrayRequest = JsonArrayRequest(url,
            { response ->
            val hotlines = Hotlines()
            for (i in 0 until response.length()) {
                val jsonObject = response.getJSONObject(i)
                try {
                    hotlines.phone_id = jsonObject.getInt("phone_id")
                    hotlines.phone_name = jsonObject.getString("phone_name")
                    hotlines.phone_number = jsonObject.getInt("phone_number")
                    hotlineList.add(hotlines)
                } catch (e: JSONException){
                    e.printStackTrace()
                }
                adapter.notifyDataSetChanged()
            }
        }
        ) { error ->
            Log.e("Volley", "$error")
        }
        queue.add(jsonArrayRequest)
    }

    private fun postHotLines(){}



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goBack(){
        findNavController().navigate(R.id.action_messageFragment_to_homeFragment)
    }

    override fun onItemsClicked(hotlines: Hotlines) {
        // will be added events for the clicking of the numbers here.
    }


}