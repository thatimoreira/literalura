package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.anoDeNascimento <= :ano AND (a.anoDeObito IS NULL OR a.anoDeObito >= :ano)")
    List<Autor> findAutoresVivosNoAno(@Param("ano") int ano);
}
