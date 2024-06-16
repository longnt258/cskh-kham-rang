using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.models
{
    public class Schedule
    {
        [JsonProperty(PropertyName = "code")]
        public string Code { get; set; }
        [JsonProperty(PropertyName = "title")]
        public string Title {  get; set; }
        [JsonProperty(PropertyName = "description")]
        public string Description { get; set; }
        [JsonProperty(PropertyName = "bookingDatetime")]
        public string BookDateTime { get; set; }
        [JsonProperty(PropertyName = "dentistName")]
        public string DentistName {  get; set; }
        [JsonProperty(PropertyName = "userFullName")]
        public string UserFullName {  get; set; }
        [JsonProperty(PropertyName = "status")]
        public int Status {  get; set; }
        [JsonProperty(PropertyName ="userId", NullValueHandling = NullValueHandling.Ignore)]
        public int UserId { get; set; }
        [JsonProperty(PropertyName = "dentistId",NullValueHandling = NullValueHandling.Ignore)]
        public int DentistId { get; set; }
        [JsonProperty(PropertyName = "phoneNumber")]
        public string PhoneNumber { get; set; }

        public override string ToString()
        {
            var userIdText = UserId != 0 ? UserId.ToString() : "N/A";
            var dentistIdText = DentistId != 0 ? DentistId.ToString() : "N/A";

            return $"Schedule: {{ Code: {Code}, Title: {Title}, Description: {Description}, " +
                   $"Booking DateTime: {BookDateTime}, Dentist Name: {DentistName}, " +
                   $"User Full Name: {UserFullName}, Status: {Status}, User ID: {userIdText}, " +
                   $"Dentist ID: {dentistIdText} }}";
        }

    }
}
