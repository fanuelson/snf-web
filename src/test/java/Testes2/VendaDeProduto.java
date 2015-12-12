package Testes2;


public class VendaDeProduto { 
	
		private Log log;  
	
		public VendaDeProduto(Log logVenda) { 
		
			this.log = logVenda; 

		}
		
		public void vendeProduto(Produto produto) { 
			
			//Todo o código para a venda do produto... 
			
			log.grava(produto); 
			
		}
}
	

	

	

