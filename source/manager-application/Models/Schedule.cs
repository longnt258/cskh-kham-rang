using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.models
{
    internal class Schedule
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
    }
}
