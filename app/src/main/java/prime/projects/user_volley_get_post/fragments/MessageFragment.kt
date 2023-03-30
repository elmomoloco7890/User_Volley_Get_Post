package prime.projects.user_volley_get_post.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import prime.projects.user_volley_get_post.R
import prime.projects.user_volley_get_post.databinding.FragmentMessageBinding


class MessageFragment : Fragment() {

    //the binding variable
    private var binding: FragmentMessageBinding ?= null

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
        binding?.apply {
            messageFragment = this@MessageFragment
        }
        getUsers()
    }

    private fun getUsers(){
        val queue = Volley.newRequestQueue(requireActivity())
        val url = "https://api.github.com/search/users?q=eyehunt"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val strResp = response.toString()
                val jsonObj= JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("items")
                var strUser = ""

                for (i in 0 until jsonArray.length()){
                    val jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    strUser =  strUser + "\n" + jsonInner.get("login")
                }
                binding?.resultTv?.text = getString(R.string.response_str_user, strUser)
            },
            {
                binding?.resultTv?.text = getString(R.string.error_response)
            }
        )
        queue.add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goBack(){
        findNavController().navigate(R.id.action_messageFragment_to_homeFragment)
    }


}