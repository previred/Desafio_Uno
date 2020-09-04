using System;
using System.Collections.Generic;
using System.Text;

namespace DTO
{
    public class DTO_TransactionType
    {
        public string transactionNumber { get; set; }
        public string transactionSeverity { get; set; }
        public string transactionState { get; set; }
        public string transactionProcedure { get; set; }
        public string transactionLine { get; set; }
        public string transactionMessage { get; set; }
    }
}
