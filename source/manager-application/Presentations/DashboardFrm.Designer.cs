namespace manager_application
{
    partial class DashboardFrm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        { 
            this.panelContent = new System.Windows.Forms.Panel();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.btnSetting = new System.Windows.Forms.Button();
            this.btnDentist = new System.Windows.Forms.Button();
            this.btnHistoryCall = new System.Windows.Forms.Button();
            this.btnCustomer = new System.Windows.Forms.Button();
            this.btnSchedule = new System.Windows.Forms.Button();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.tableLayoutPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // panelContent
            // 
            this.panelContent.Location = new System.Drawing.Point(220, 2);
            this.panelContent.Name = "panelContent";
            this.panelContent.Size = new System.Drawing.Size(1043, 667);
            this.panelContent.TabIndex = 1;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel1.Controls.Add(this.btnSetting, 0, 4);
            this.tableLayoutPanel1.Controls.Add(this.btnDentist, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.btnHistoryCall, 0, 3);
            this.tableLayoutPanel1.Controls.Add(this.btnCustomer, 0, 1);
            this.tableLayoutPanel1.Controls.Add(this.btnSchedule, 0, 2);
            this.tableLayoutPanel1.Location = new System.Drawing.Point(3, 187);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 5;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 20F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(214, 482);
            this.tableLayoutPanel1.TabIndex = 2;
            // 
            // btnSetting
            // 
            this.btnSetting.Location = new System.Drawing.Point(0, 384);
            this.btnSetting.Margin = new System.Windows.Forms.Padding(0);
            this.btnSetting.Name = "btnSetting";
            this.btnSetting.Size = new System.Drawing.Size(218, 98);
            this.btnSetting.TabIndex = 9;
            this.btnSetting.Text = "Thông báo";
            this.btnSetting.UseVisualStyleBackColor = true;
            this.btnSetting.Click += new System.EventHandler(this.BtnSetting_Click);
            // 
            // btnDentist
            // 
            this.btnDentist.Location = new System.Drawing.Point(0, 0);
            this.btnDentist.Margin = new System.Windows.Forms.Padding(0);
            this.btnDentist.Name = "btnDentist";
            this.btnDentist.Size = new System.Drawing.Size(218, 96);
            this.btnDentist.TabIndex = 5;
            this.btnDentist.Text = "Nha sĩ";
            this.btnDentist.UseVisualStyleBackColor = true;
            this.btnDentist.Click += new System.EventHandler(this.BtnDentist_Click);
            // 
            // btnHistoryCall
            // 
            this.btnHistoryCall.Location = new System.Drawing.Point(0, 288);
            this.btnHistoryCall.Margin = new System.Windows.Forms.Padding(0);
            this.btnHistoryCall.Name = "btnHistoryCall";
            this.btnHistoryCall.Size = new System.Drawing.Size(218, 96);
            this.btnHistoryCall.TabIndex = 8;
            this.btnHistoryCall.Text = "Lịch sử cuộc gọi";
            this.btnHistoryCall.UseVisualStyleBackColor = true;
            this.btnHistoryCall.Click += new System.EventHandler(this.BtnHistoryCall_Click);
            // 
            // btnCustomer
            // 
            this.btnCustomer.Location = new System.Drawing.Point(0, 96);
            this.btnCustomer.Margin = new System.Windows.Forms.Padding(0);
            this.btnCustomer.Name = "btnCustomer";
            this.btnCustomer.Size = new System.Drawing.Size(218, 96);
            this.btnCustomer.TabIndex = 6;
            this.btnCustomer.Text = "Khách hàng";
            this.btnCustomer.UseVisualStyleBackColor = true;
            this.btnCustomer.Click += new System.EventHandler(this.BtnCustomer_Click);
            // 
            // btnSchedule
            // 
            this.btnSchedule.Location = new System.Drawing.Point(0, 192);
            this.btnSchedule.Margin = new System.Windows.Forms.Padding(0);
            this.btnSchedule.Name = "btnSchedule";
            this.btnSchedule.Size = new System.Drawing.Size(218, 96);
            this.btnSchedule.TabIndex = 7;
            this.btnSchedule.Text = "Lịch";
            this.btnSchedule.UseVisualStyleBackColor = true;
            this.btnSchedule.Click += new System.EventHandler(this.BtnSchedule_Click);
            // 
            // pictureBox1
            // 
            this.pictureBox1.Image = global::manager_application.Properties.Resources.OIP;
            this.pictureBox1.Location = new System.Drawing.Point(12, 12);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(202, 169);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 3;
            this.pictureBox1.TabStop = false;
            // 
            // DashboardFrm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.WhiteSmoke;
            this.ClientSize = new System.Drawing.Size(1264, 681);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.panelContent);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "DashboardFrm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Nha Khoa Nha Việt";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.DashboardFrm_FormClosing);
            this.tableLayoutPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panelContent;
        private System.Windows.Forms.Button btnSetting;
        private System.Windows.Forms.Button btnHistoryCall;
        private System.Windows.Forms.Button btnSchedule;
        private System.Windows.Forms.Button btnCustomer;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Button btnDentist;
        private System.Windows.Forms.PictureBox pictureBox1;
    }
}