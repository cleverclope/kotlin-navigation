package clever.arinda.kotlinlearnerenrollment.ui.home.userDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import clever.arinda.kotlinlearnerenrollment.R
import clever.arinda.kotlinlearnerenrollment.database.entities.RegistrationEntity
import clever.arinda.kotlinlearnerenrollment.databinding.ListItemBinding

class MyRecycleViewAdapter(private val usersList: List<RegistrationEntity>) :
    RecyclerView.Adapter<MyviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.bind(usersList[position])

    }

}

class MyviewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: RegistrationEntity) {
        binding.FirstNameTextView.text = user.fullname
        binding.secondNameTextView.text = user.email
        binding.userTextField.text = user.username
    }

}