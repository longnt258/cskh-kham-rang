using manager_application.models;
using manager_application.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace manager_application.Services
{
    internal class AuthServices
    {
        private readonly HttpClient _client = APIService.GetInstance().GetHttpClient();
        private HttpResponseMessage _response;

        public async Task<Response<Admin>> Login(string username, string password)
        {
            try
            {
                Admin admin = new Admin
                {
                    UserName = username,
                    Password = password
                };

                _response = await _client.PostAsJsonAsync("auth/admin/login", admin);
                _response.EnsureSuccessStatusCode();

                // Deserialize the response content to Response<Admin>
                Response<Admin> responseData = await _response.Content.ReadAsAsync<Response<Admin>>();

                return responseData;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex);
            }
        }

        public async Task<Response<Customer>> registerNewCus(Customer user)
        {
            try
            {
                _response = await _client.PostAsJsonAsync("auth/user/register", user);
                Response<Customer> responseData = await _response.Content.ReadAsAsync<Response<Customer>>();
                return responseData;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message, ex);
            }
        }
    }
}


