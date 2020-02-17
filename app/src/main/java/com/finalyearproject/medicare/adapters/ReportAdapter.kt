package com.finalyearproject.medicare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.finalyearproject.medicare.R
import com.finalyearproject.medicare.helpers.Constants
import com.finalyearproject.medicare.models.ReportModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.item_report.view.*


open class ReportAdapter(
    var context: Context,
    var fragment: Fragment?,
    var reports: ArrayList<ReportModel>
) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    private val requestData = JsonObject()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_report,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return reports.size
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reports[position]

        holder.itemView.report_patient_id_text.text = reports[position].patientId
        holder.itemView.report_doctor_id_text.text = reports[position].doctorId
        holder.itemView.report_patient_name_text.text = reports[position].patientName
        holder.itemView.report_doctor_name_text.text = reports[position].doctorName
        holder.itemView.report_id_text.text = reports[position].reportId.toString()
        holder.itemView.report_desc_text.text = reports[position].reportDescription
        holder.itemView.report_title_text.text = reports[position].reportTitle
        holder.itemView.report_date_text.text = reports[position].date
        holder.itemView.report_status_text.text = reports[position].collectingStatus

        requestData.addProperty("patientId", reports[position].patientId)
        requestData.addProperty("reportId", reports[position].reportId.toString())


        holder.itemView.setOnClickListener {
            if (reports[position].collectingStatus == Constants.STATUS_PENDING) {
                val callback = context as ReportCallbackInterface
                callback.onAddReportClick(requestData)
            } else {
                Toast.makeText(context, "Already Uploaded.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface ReportCallbackInterface {
        fun onAddReportClick(requestData: JsonObject)
    }
}