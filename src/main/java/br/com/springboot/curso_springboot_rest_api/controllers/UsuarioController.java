package br.com.springboot.curso_springboot_rest_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import br.com.springboot.curso_springboot_rest_api.repository.UsuarioRepository;
import br.com.springboot.curso_springboot_rest_api.model.Usuario;

@RestController
public class UsuarioController {


	@Autowired
	private UsuarioRepository usuarioRepository;
    
    @GetMapping(value = "listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	List<Usuario>  usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
    @PostMapping(value = "salvar")
    @ResponseBody 
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "deletar")
    @ResponseBody 
    public ResponseEntity<String> salvar(@RequestParam(name="idUser") Long idUser){
    	usuarioRepository.deleteById(idUser);
    	return new ResponseEntity<String>("Deletado com sucesso!", HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarUserId")
    @ResponseBody 
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name="idUser") Long idUser){
    	Usuario user = usuarioRepository.findById(idUser).get();
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @PutMapping(value = "atualizar")
    @ResponseBody 
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado!",HttpStatus.BAD_REQUEST);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @GetMapping(value = "buscarPorNome")
    @ResponseBody 
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name="nome") String nome){
    	List<Usuario> user = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
    }
    
    
	
	
}
