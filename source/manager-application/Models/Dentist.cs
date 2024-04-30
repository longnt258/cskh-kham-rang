using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.models
{
    public class Dentist
    {
        public Dentist() { }
        [JsonProperty(PropertyName = "dentistId")]
        public int DentistId { get; set; }
        [JsonProperty(PropertyName = "fullName")]
        public string FullName {  get; set; }
        [JsonProperty(PropertyName = "status")]
        public bool Status {  get; set; }
        [JsonProperty(PropertyName = "endDateTime")]
        public DateTime EndDateTime { get; set; }
        [JsonProperty(PropertyName = "startDateTime")]
        public DateTime StartDateTime { get; set; }
        [JsonProperty(PropertyName = "startDateTimeString")]
        public string StartDateTimeString { get; set; }
        [JsonProperty(PropertyName = "endDateTimeString")]
        public string EndDateTimeString { get; set; }
    }
}
