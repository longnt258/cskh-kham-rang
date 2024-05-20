using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.Services
{
    internal class APIService
    {
        private static HttpClient _httpClient;
        private static APIService instance = GetInstance();
        private APIService()
        {
            _httpClient = new HttpClient();
            _httpClient.BaseAddress = new Uri("http://localhost:8080/api/v1/");
            _httpClient.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
        }


        public static APIService GetInstance()
        {
            if (instance == null)
            {
                instance = new APIService();
                return instance;
            }
            return instance;
        }
        public HttpClient GetHttpClient()
        {
            return _httpClient;
        }
    }
}
