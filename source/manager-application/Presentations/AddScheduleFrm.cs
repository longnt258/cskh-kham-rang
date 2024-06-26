using manager_application.models;
using manager_application.Models;
using manager_application.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace manager_application
{
    public partial class AddScheduleFrm : Form
    {
        private readonly CustomerService customerService;
        private readonly DentistService dentistService;
        private readonly AuthServices authServices;
        private readonly ScheduleService scheduleService;
        private List<Dentist> dentists;
        private Dentist selectedDentist;
        private Customer cus;

        public AddScheduleFrm()
        {
            scheduleService = new ScheduleService();
            customerService = new CustomerService();
            dentistService = new DentistService();
            authServices = new AuthServices();
            InitView();
            InitializeComponent();
        }

        private async void InitView()
        {
            Response<List<Dentist>> respone = await dentistService.GetAllDentist();
            if(respone.Status == 0) 
            {
                MessageBox.Show("Lỗi Không thể lấy danh sách Nha sĩ");
            }
            else
            {
                dentists = respone.data;
                comboBox1.Items.Clear();
                foreach(Dentist dentist in dentists)
                {
                    comboBox1.Items.Add(dentist.FullName);
                }
               
            }
        }

        private void TbPhoneNumber_KeyPress(object sender, KeyPressEventArgs e)
        {
            // Chỉ chấp nhận chữ số, dấu cách và dấu ngắt số (Nếu cần thiết)
            if (!char.IsDigit(e.KeyChar) && !char.IsControl(e.KeyChar) && (e.KeyChar != '.' || tbPhoneNumber.Text.Contains('.')))
            {
                e.Handled = true; 
            }
        }

        private async void BtnFindCus_Click(object sender, EventArgs e)
        {
            Response<Customer> res = await customerService.FindCustomerByPhone(tbPhoneNumber.Text);
            if(res.data == null)
            {
                MessageBox.Show("Không tìm thấy khách hàng");
            }
            else
            {
                MessageBox.Show("Đã tìm thấy khách hàng!");
                cus = res.data;
                tbPhoneNumber.Text = cus.PhoneNumber;
                tbCusEmail.Text = cus.Email;
                tbCusFullName.Text = cus.FullName;
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            selectedDentist = dentists[comboBox1.SelectedIndex];
        }

        private async void btnOk_Click(object sender, EventArgs e)
        {
            string selectedDate = DateTimePickerBookDate.Value.Date.ToString("dd/MM/yyyy");
            string selectedTime = TimePickerBookTime.Value.ToString("HH:mm:ss");
            string dateTimeSelected = $"{selectedDate} {selectedTime}";
            string description = tbDes.Text;
            string title = rtbTitle.Text;

            if (selectedDentist == null)
            {
                MessageBox.Show("Vui lòng chọn bác sĩ!");
                comboBox1.Focus();
                return;
            }

            if (cus == null)
            {
                MessageBox.Show("Không có thông tin khách hàng!");
                return;
            }

            Schedule schedule = new Schedule()
            {
                BookDateTime = dateTimeSelected,
                DentistId = selectedDentist.DentistId,
                Description = description,
                Status = 2,
                UserId = cus.Id,
                Title = title
            };
      
            Response<Schedule> res = await scheduleService.insert(schedule);
            if(res.Status == 1)
            {
                MessageBox.Show("Đặt lịch thành công!");
                Close();
            }
            else
            {
                MessageBox.Show("Có lỗi xảy ra! Vui lòng thử lại");
                Close();
            }
            
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private async void BtnThemKhachHang_Click(object sender, EventArgs e)
        {
            string usernmae = tbPhoneNumber.Text;
            string phoneNumber = tbPhoneNumber.Text;
            string email = tbCusEmail.Text;
            string fullName = tbCusFullName.Text;
            string password = "123456";

            Customer customer = new Customer()
            {
                Email = email,
                PhoneNumber = phoneNumber,
                FullName = fullName,
                UserName = usernmae,
                Password = password,
            };
            if (customer == null)
            {
                MessageBox.Show("Không có thông tin khách hàng!");
                return;
            }
            Response<Customer> resCus = await authServices.registerNewCus(customer);
            if (resCus.Status == 1 && resCus.data != null)
            {
                cus = resCus.data;
                tbPhoneNumber.Text = cus.PhoneNumber;
                tbCusFullName.Text = cus.FullName;
                tbCusEmail.Text = cus.Email;
                MessageBox.Show("Đăng kí thành công!");
            }
            else
            {
                MessageBox.Show(resCus.Message);
            }
        }
    }
}
