using manager_application.models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.Services
{
    internal class LoginService
    {
        public HttpClient _client;
        public HttpResponseMessage _response;
        public LoginService() 
        {
            _client = new HttpClient();
            _client.BaseAddress = new Uri("http://localhost:8080/api/auth");
            _client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
        }
    }
}
