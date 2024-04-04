using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.models
{
    internal class Dentist
    {
        public Dentist() { }
        public int DentistId { get; set; }
        public string FullName {  get; set; }
        public bool Status {  get; set; }
        public DateTime EndDateTime { get; set; }
        public DateTime StartDateTime { get; set; }
    }
}
