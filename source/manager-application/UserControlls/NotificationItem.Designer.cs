namespace manager_application.UserControlls
{
    partial class NotificationItem
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.lbTitle = new System.Windows.Forms.Label();
            this.lbContent = new System.Windows.Forms.Label();
            this.lbNotifTime = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // lbTitle
            // 
            this.lbTitle.AutoSize = true;
            this.lbTitle.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbTitle.Location = new System.Drawing.Point(452, 11);
            this.lbTitle.Name = "lbTitle";
            this.lbTitle.Size = new System.Drawing.Size(135, 25);
            this.lbTitle.TabIndex = 0;
            this.lbTitle.Text = "Lịch sắp đến";
            // 
            // lbContent
            // 
            this.lbContent.AutoSize = true;
            this.lbContent.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbContent.Location = new System.Drawing.Point(21, 53);
            this.lbContent.Name = "lbContent";
            this.lbContent.Size = new System.Drawing.Size(66, 20);
            this.lbContent.TabIndex = 1;
            this.lbContent.Text = "Content";
            // 
            // lbNotifTime
            // 
            this.lbNotifTime.AutoSize = true;
            this.lbNotifTime.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lbNotifTime.Location = new System.Drawing.Point(883, 56);
            this.lbNotifTime.Name = "lbNotifTime";
            this.lbNotifTime.Size = new System.Drawing.Size(34, 17);
            this.lbNotifTime.TabIndex = 2;
            this.lbNotifTime.Text = "time";
            // 
            // NotificationItem
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ActiveCaption;
            this.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Controls.Add(this.lbNotifTime);
            this.Controls.Add(this.lbContent);
            this.Controls.Add(this.lbTitle);
            this.Name = "NotificationItem";
            this.Size = new System.Drawing.Size(1032, 96);
            this.MouseClick += new System.Windows.Forms.MouseEventHandler(this.NotificationItem_MouseClick);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbTitle;
        private System.Windows.Forms.Label lbContent;
        private System.Windows.Forms.Label lbNotifTime;
    }
}
