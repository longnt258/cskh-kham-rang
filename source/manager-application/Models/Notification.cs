using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.Models
{
    internal class Notification
    {
        [JsonProperty("notificationId")]
        public int Id { get; set; }
        [JsonProperty("content")]
        public string Content { get; set; }
        [JsonProperty("status")]
        public bool Status { get; set; }
        [JsonProperty("createdDate")]
        public DateTime CreatedDate { get; set; }

        public override string ToString()
        {
            return "Content " + Content;
        }
    }
}
