// Paquete
package com.udecsanitas.service;

// Librerías
import javax.ejb.EJB;
import java.util.Date;
import javax.ejb.Stateless;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.modelmapper.ModelMapper;
import com.udecsanitas.entity.Medico;
import com.udecsanitas.entity.Seguridad;
import com.udecsanitas.utilitarie.UToken;
import com.udecsanitas.utilitarie.UMedico;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import com.udecsanitas.exception.BadRequestException;
import com.udecsanitas.exception.UnauthorizedException;
import com.udecsanitas.service.interfaz.ISeguridadService;
import com.udecsanitas.repository.interfaz.IMedicoRepository;
import com.udecsanitas.repository.interfaz.ISeguridadRepository;

/**
 * Capa de servicios de seguridad
 * @author Jhonattan Pulido
 * @version 1.0.0
 * @since 01/06/2021
 */
@Stateless
public class SeguridadService implements ISeguridadService {

    // Variables   
    @EJB
    private ISeguridadRepository seguridadRepository;
    
    @EJB
    private IMedicoRepository medicoRepository;
    
    // Métodos
    
    /**
     * Iniciar sesión
     * @param correoElectronico
     * @param clave
     * @return Token
     * @throws UnauthorizedException 
     * @throws BadRequestException
     */
    @Override
    public UToken iniciarSesion(String correoElectronico, String clave) throws  UnauthorizedException,
                                                                                BadRequestException {        
        if (seguridadRepository.cantidadEmail(correoElectronico) == 0) {
            
            clave = DigestUtils.shaHex(clave); // Encriptando la clave para compararla
            
            Medico med = medicoRepository.leer(correoElectronico, clave);
            
            if (med != null) {
            
                UMedico medico = new ModelMapper().map(med, UMedico.class);                                    
                medico.setDireccion(null);
                medico.getRol().setNombre(medicoRepository.leerRol(medico.getRol().getId()));
                
                long tiempo = System.currentTimeMillis();                
                
                String jwt = Jwts.builder()
                                .signWith(SignatureAlgorithm.HS512, secretKey)
                                .setSubject(medico.getCorreoElectronico())
                                .setIssuedAt(new Date(tiempo))
                                .setExpiration(new Date(tiempo + 900000))
                                .claim("rol", medico.getRol().getNombre())
                                .compact();
                
                Seguridad seguridad = new Seguridad();
                seguridad.setToken(jwt);
                seguridad.setCorreoElectronico(correoElectronico);
                
                seguridadRepository.crear(seguridad);
                
                return new UToken(jwt);
                
            } else
                throw new UnauthorizedException("Datos incorrectos");
            
        } else
            throw new BadRequestException("El médico ya inició sesión");
        
    }

    /**
     * Validar integridad del token - La fecha de expiración se valida automáticamente
     * @param token 
     * @param url 
     * @throws UnauthorizedException
     */
    @Override
    public void validarToken(String token, String url) throws   UnauthorizedException {
        
        Claims claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token).getBody();               
        
        if (seguridadRepository.cantidad(token, claims.get("sub", String.class)) == 1)
            validarRol(claims.get("rol", String.class), url);
        else
            throw new UnauthorizedException("No se encontró el token específicado");
               
    }
    
    /**
     * Válidar las acciones que puede realizar el usuario
     * @param rol
     * @param url
     * @throws UnauthorizedException 
     */
    private void validarRol(String rol, String url) throws  UnauthorizedException {
        
        switch (rol) {
        
            case "Administrador":
                if (!url.contains("medicos"))
                    throw new UnauthorizedException("No está autorizado para realizar esta acción");
                break;
                
            case "Doctor":
                if (url.contains("examenes") == false && url.contains("consultas") == false)
                    throw new UnauthorizedException("No está autorizado para realizar esta acción");
                break;                
                
            default:
                throw new UnauthorizedException("El token es incorrecto");            
        }                
        
    }
    
}
