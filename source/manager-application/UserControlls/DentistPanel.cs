using manager_application.models;
using manager_application.Models;
using manager_application.Services;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace manager_application.UserControlls
{
    public partial class DentistPanel : UserControl
    {
        private readonly DentistService dentistAPI;

        public DentistPanel()
        {
            InitializeComponent();
            dentistAPI = new DentistService();
            InitView();
        }

        async void InitView()
        {
            dataGridView1.Rows.Clear();
            Response<List<Dentist>> res = await dentistAPI.GetAllDentist(); 
            if(res.Status == 1) 
            {
                for (int i= 0; i < res.data.Count; i++)
                {
                    Dentist dentist = res.data[i];
                    dataGridView1.Rows.Add(new object[] 
                    { 
                        i.ToString(),
                        dentist.FullName,
                        dentist.StartDateTime.ToString(),
                        dentist.EndDateTime.ToString() ,
                        dentist.Status? "Đang hoạt động" : "Không hoạt động"
                    });
                }
            }
        }

        private void BtnRefresh_Click(object sender, EventArgs e)
        {
            InitView();
        }
    }
}
