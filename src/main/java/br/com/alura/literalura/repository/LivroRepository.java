package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @EntityGraph(attributePaths = {"autores", "idiomas"})
    List<Livro> findAll();

    @EntityGraph(attributePaths = {"autores", "idiomas"})
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    @EntityGraph(attributePaths = {"autores", "idiomas"})
    List<Livro> findByIdiomasContaining(String idioma);
    @Query("SELECT COUNT(1) FROM Livro l JOIN l.idiomas i WHERE i = :idioma")
    long countByIdioma(@Param("idioma") String idioma);
}
