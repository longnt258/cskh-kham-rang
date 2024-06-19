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
    internal class NotificationService
    {
        private readonly HttpClient _client = APIService.GetInstance().GetHttpClient();
        private HttpResponseMessage _response;

        // Lấy toàn bộ thông báo
        public async Task<List<Notification>> GetAllNotification()
        {
            try
            {
                _response = await _client.GetAsync("notification");
                _response.EnsureSuccessStatusCode();
                if (_response.IsSuccessStatusCode)
                {
                    List<Notification> list = await _response.Content.ReadAsAsync<List<Notification>>();
                    return list;
                }
                return null;
            }
            catch (Exception ex) { throw new Exception(ex.Message, ex); }
        }

        // Cập nhật thông báo dựa trên id của thông báo
        public async Task<Notification> UpdateNotificatioNStatus(int notificationId)
        {
           
            try
            {
                _response = await _client.PostAsync($"notification?notificationId={notificationId}",null);
                _response.EnsureSuccessStatusCode();
                if (_response.IsSuccessStatusCode)
                {
                    Notification list = await _response.Content.ReadAsAsync<Notification>();
                    return list;
                }
                return null;
            }
            catch (Exception ex) { throw new Exception(ex.Message, ex); }
        }
    }
}
